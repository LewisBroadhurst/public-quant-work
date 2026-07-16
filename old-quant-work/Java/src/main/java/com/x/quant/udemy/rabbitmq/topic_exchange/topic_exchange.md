## Topic Exchange
- Producer publishes a message to the "topic" exchange
- Each queue has a routing key, such as `"*.mobile"` or `"tv.*"` or `"#.ac"` etc.
- The message published has a key seperated by dots, such as "tv.mobile.ac"
- Does not copy keys exactly, but uses a pattern to match keys

___

Example keys:
- `"*.mobile"` - will match any key that ends with mobile
  - e.g. "samsung.mobile", "apple.mobile"
  - will not match "samsung.mobile.ac", "apple.iphone.mobile"


- `"tv.*"` - will match any key that starts with tv
  - e.g. "tv.samsung", "tv.apple"
  - will not match "samsung.tv", "apple.tv.samsung"


- `"*.ac.*"` - will match any key that has ac in the middle
    - e.g. "samsung.ac.2021", "apple.ac.2022"
    - will not match "samsung.ac", "apple.ac"


- `"#.ac.*"` - # means 1 or more than 1 word before ac. * means exactly 1 word before.
    - e.g. "example.samsung.ac.2021", "apple.ac.2022", "samsung.sony.ac.2021"
    - will not match "samsung.ac", "apple.ac", "ac.2021"

![img.png](img.png)