# Bonwal Product Purchase service coding task


### Description

Your task is to implement a purchase service core. You don't need to implement any UI, only core service which could be later used by an UI or some HTTP request handler.  
With this service you should be able to maintain a list of products (or product types) which can be sold using this same service and get a report of sold items for any requested timeframe.  

##### Example products 
```
Just an example, no need to use these products
   - movie ticket, 5,00€
   - soda, 2,00€
   - popcorn, 2,00€
   - popcors + soda, 3,50€
   - movie ticket + soda, 6,00€
   - movie ticket + popcorn + soda 8,00€
```

This task should be done with Test driven development in mind. Create tests you see fit, you can create additional test files if needed. 

Implement all given interfaces located in Interfaces folder. You can create additional classes as much as you want, but do not change interface specification other than possibly adding extra "throws *MyCustomException*" declarations to interfaces if needed. 

It is suggested, that you should use custom Exceptions in places, which those are suitable for. 

Purchase service should keep track of purchased items, but there is no need for databases or persistent storage. Everything can be done in runtime memory. 

Meaning that it is okay to lose the data, after test is run. 


### Requirements

- Java 11, which can be located from https://adoptium.net/temurin/releases/?version=11 
- IDE. You can use any IDE you wish, but VSCode is suggested if you do not have Java development environment already installed.  

- VSCode can be downloaded from https://code.visualstudio.com/
- VSCode Java extension can be downloaded from https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack


