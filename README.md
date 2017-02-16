# ILoveZappos
Its a basic android application, built on top of Zappos Rest Api, that takes input from the user as a serach querry and returns the items as a product page.

HighLights- 
1) For consuming the REST api volley framework is used.
   Appsingelton is the class which has the function for consuming the API. Images are loaded using the ImageLoader class of Volley Framework.
2) API returns results in JSON format,MyModel is the class, which is used as the datastructure class for the data.
3) Data Binding Library is used for linking the UI, with the search results.
4) Products are showcased in a cardview. For filling the details recyclerview adapter is used.
5) 9-Patch launcher image is used.
6) Bubble animation is used for providing feedback to the user's action.
7) Material design is considered whereever required, inorder to ensure that the application is scalable across all the devices.
8) Floating action button is used for showcasing the cart.
