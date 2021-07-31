# TestTaskForWamisoftware
### Test task for Wamisoftware

<img src="https://scontent.fiev25-1.fna.fbcdn.net/v/t1.6435-9/67293121_395225247780799_5567716558509178880_n.png?_nc_cat=106&ccb=1-3&_nc_sid=e3f864&_nc_ohc=azdGRlegDBgAX8x48Yg&_nc_ht=scontent.fiev25-1.fna&oh=1fa5faae445741b7a08c2d657587525d&oe=61291737" alt="Introduction Banner.." style="text-align: center; margin-bottom: 23px;" />

Task

-   :computer: You only need to use **java 8, stream api, collections**
-   :monocle_face: 
       1.  Using Parsing Logic, get information from **tag_read_start** when repeating tags, use the first occurrence.
       2. Using Parsing Logic, get information from **tag_read_finish** when repeating tags, use the last occurrence.
       3. Display the 10 participants who spent the least time in this competition in the console.
     
     There are two files.
-   :seedling: **tag_read_start** - contains logs with participant id and start time. 
-   :heart: **tag_read_finish** - contains logs with participant id and finish time.
-   :penguin: **Need to use Parsing Logic!**
 
Parsing Logic 
```
TagRead example:

aaE4058001c6df0700011608131047512800FS
----058001c6df07----160813104751------


//Index start from 0
TAG_STARTS_AT = 4;
TAG_ENDS_AT = 16; //exclusive
TIMESTAMP_STARTS_AT = 20;
TIMESTAMP_ENDS_AT = 32; //exclusive

Timestamp parsing pattern: "yyMMddHHmmss"
```
