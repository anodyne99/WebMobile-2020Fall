import { Component } from '@angular/core';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {

  // sets the variable type of string
  // Leaving this without ":string" caused all sorts of issues
  newEntry: string;
  // Creates the empty list
  items = [];
  // Allows the object to be made
  todoObj: any;


  // Very roundabout way to cross out text but it works
  strikeThrough(text) {
    return text
      .split('') // Splits the string into a list of characters //
      .map(char => char + '\u0336') // Changes each character to the equivalent unicode strikethrough companion //
      .join(''); // Puts the characters back together in a new, converted string //
  }

  // Creates the new object todoObj and assigns it the necessary attribute
  submitNewItem() {
    // With this part it will push if and only if the input isn't blank
    if (this.newEntry !== '') {
      this.todoObj = {
        newEntry: this.newEntry,
      };
      // Appends to the end of the list
      this.items.push(this.todoObj.newEntry);
      // resets the newEntry variable for error handling
      this.newEntry = '';
    }
  }

  // Calls the function to iterate over the string and replaces it in-place
  // I spent literally all night getting this part to behave. 8 hours on making this behave. I feel like death.
  completeItem(element, itemIndex) {
    const temp = element;
    const newTemp = this.strikeThrough(temp);
    this.items.splice(itemIndex, 1, newTemp);
  }

  // Finds and deletes the item at the index when you press the delete button
  deleteItem(itemIndex) {
    this.items.splice(itemIndex, 1);
    // alert(this.items); (for testing only)
  }

}
