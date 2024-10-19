# Program Analysis
Have a close look at the source code and try to identify existing deficiencies: Which drawbacks and
weaknesses does the current solution have?


OrderService.class:
- The same thing happens in the functions for sorting products and services => Can also be summarised in one function
- The functions for ordering products and services are very similar => Can also be summarised in one function
- Variable names are not meaningful: var10000 and var10001
- Assigning System.out to a variable (var10000 = System.out) is unnecessary and makes the code less understandable
- var10001 = String.valueOf(this.products[i]);
- Converting this.products[i] into a string (var10001 = String.valueOf(this.products[i]);) in order to use it in println in a concatenation is unnecessary, as in Java a string conversion is automatically carried out anyway when concatenating strings with other values.
- The output in the for loops in finishOrder() for each product and service can be summarised in a separate function.
- var10001 is first used for the formatted individual prices and then for the formatted sum => individual variables would be useful for clarity
- return priceInCent / 100 + ‘.’ + (priceInCent % 100 < 10 ? ‘0’ : ‘’) + priceInCent % 100 + ‘ EUR’; could be made more readable and shorter by return String.format(‘%.2f EUR’, priceInCent / 100.0);.

Product.class & Service.class
- Product.class and Service.class have a similar structure => a common superclass would be useful

