import { Component, AfterViewInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterViewInit {
  title = 'LibraryClient';
  books: any[] = []; // Define the books property as an empty array

  async loadBookList() {
    const apiUrl = 'http://localhost:8082/library/api/books';

    try {
      const response = await fetch(apiUrl);
      this.books = await response.json(); // Assign the retrieved books to the books property

    } catch (error) {
      console.error('Error loading book list:', error);
    }
  }

  ngAfterViewInit() {
    this.loadBookList();
  }

  selectedRow: number = -1;

  onRowClick(index: number) {
    this.selectedRow = index;
  }

  isRowSelected(index: number): boolean {
    return this.selectedRow === index;
  }

  async onBorrowClick() {
    if (this.selectedRow !== -1) {
      const bookTitle = this.books[this.selectedRow].title;
      console.log('Borrow button clicked. Book title:', bookTitle);
      const book = await (await fetch("http://localhost:8082/library/api/books/title/" + bookTitle)).json();

      console.log(book.id);

      const borrowUrl = `http://localhost:8082/library/api/borrows/add?book_id=${book.id}&subscriber_id=daweed.eduard`;
      await fetch(borrowUrl, {method: "POST"});
      console.log('Borrow added successfully.');

      const changeReservedUrl = "http://localhost:8082/library/api/books/update"
      await fetch(changeReservedUrl, {method: "POST", headers: {
        'Content-Type': 'application/json',
      }, body:JSON.stringify(book),
      })
      console.log('Reserved status changed succesfully')
      this.loadBookList();
    }
  }

}
