# Program Analysis
Have a close look at the source code and try to identify existing deficiencies: Which drawbacks and
weaknesses does the current solution have?


OrderService.class:
- The same thing happens in the functions for sorting products and services => Can also be summarised in one function
- The functions for ordering products and services are very similar => Can also be summarised in one function
- The output in the for loops in finishOrder() for each product and service can be summarised in a separate function.
- return priceInCent / 100 + ‘.’ + (priceInCent % 100 < 10 ? ‘0’ : ‘’) + priceInCent % 100 + ‘ EUR’; could be made more readable and shorter by return String.format("%.2f EUR", priceInCent / 100.0);.
- Variable names l, p and s are not clear what the letters stand for and are used for different things

Product.class & Service.class
- Product.class and Service.class have a similar structure => a common superclass would be useful

