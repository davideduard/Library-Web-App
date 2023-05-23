import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'LibrarianClient';
  subscribers: any [] = [];
  borrows: any [] = [];
  books:any [] = [];

  showButton:boolean = false;

  async loadSubscribersList() {
    const apiUrl = 'http://localhost:8082/library/api/subscribers';

    try {
      const response = await fetch(apiUrl);
      this.subscribers = await response.json();
    

    } catch (error) {
      console.error('Error loading subscribers list:', error);
    }
  }

  async loadBorrowsForUser(id:string) {
    const apiUrl = "http://localhost:8082/library/api/borrows/" + id;

    try {
      const response = await fetch(apiUrl);
      this.borrows = await response.json();
      
      for (let borrow of this.borrows){
        let book = await (await fetch("http://localhost:8082/library/api/books/" + borrow.book_id)).json();
        this.books.push(book)
      }

    } catch (error) {
      console.error('Error loading borrows list:', error);
    }
  }

  ngAfterViewInit() {
    this.loadSubscribersList();
  }

  selectedRow: number = -1;
  selectedBookRow: number = -1;

  onRowClick(index: number) {
    this.selectedRow = index;
    this.selectedBookRow = -1;
    this.books = [];
    this.showButton = true;
    const selectedSubscriber = this.subscribers[this.selectedRow];
    const userId = selectedSubscriber.id;
    this.loadBorrowsForUser(userId);
  }

  isRowSelected(index: number): boolean {
    return this.selectedRow === index;
  }

  onBookRowClick(index: number) {
    this.selectedBookRow = index;
  }

  isBookRowSelected(index: number): boolean {
    return this.selectedBookRow === index;
  }

  async onReturnBtnClick() {
    if (this.selectedBookRow != -1) {
      const book = this.books[this.selectedBookRow];
      const bookId = book.id;
      console.log(`Return Button pressed. Book with the id ${bookId} is selected`);

      const subscriberId = this.subscribers[this.selectedRow].id;
      console.log(`Subscriber with the id ${subscriberId} is selected`);

      const apiUrl = `http://localhost:8082/library/api/returns/add/?book_id=${bookId}&subscriber_id=${subscriberId}&librarian_id=admin`
      await fetch(apiUrl, {method: "POST"});
      console.log('Return added successfully.');

      const changeReservedUrl = "http://localhost:8082/library/api/books/update"
      await fetch(changeReservedUrl, {method: "POST", headers: {
        'Content-Type': 'application/json',
      }, body:JSON.stringify(book),
      })
      console.log('Reserved status changed succesfully')

      const deleteBorrowUrl = `http://localhost:8082/library/api/borrows/delete/${bookId}/${subscriberId}`
      await fetch(deleteBorrowUrl, {method: "DELETE"})
      console.log("Deleted the borrow from the list")

      this.books=[];
    }

  }

}
