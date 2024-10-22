# Program Analysis
Have a close look at the source code and try to identify existing deficiencies: Which drawbacks and
weaknesses does the current solution have?





Services.OrderService.class:
- Fixed Array Size for Products and Services :5 products and 5 services may be ordered at once => use of dynamic ArrayList to handle any number of items.
- Duplication : The same logic  happens in the functions of sorting products and services => use of a generic method that can handle the sorting of  both products and services 
- Duplication :The same logic  happens in the functions of ordering products and services  => Can also be summarised in one function
- The output in the for loops in finishOrder() for each product and service can be summarised in a separate function.
- return priceInCent / 100 + ‘.’ + (priceInCent % 100 < 10 ? ‘0’ : ‘’) + priceInCent % 100 + ‘ EUR’; could be made more readable and shorter by return String.format("%.2f EUR", priceInCent / 100.0);.
- Variable names l, p and s are not clear what the letters stand for and are used for different things

Entities.Product.class & Entities.Service.class
- Entities.Product.class and Entities.Service.class have a similar structure => a common superclass would be useful

-Inputs : 
no invalid inputs handling for exemple for reading integers ,when reading non-integer value the program take the value of 0 =>  show error message and create a loop to make the user able to retry .
