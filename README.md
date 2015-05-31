# grid-android-designer
You can now place all your items in a relative layout, and use this library to manage and design your items from code.

# Exemples

Photshop image without grid, designed with 4 buttons.
![alt tag](/without_grid.png)

same photoshop image with grid units, each grid unit is 5% of screen size, for exemple button "test" has 3.1 GRID units from left, and 3.5 GRID units from top, same for all buttons.

![alt tag](/with_grid.png)

then when using grid-android-designer, just drop all your views in one relative layout, and the magic will show!

![alt tag](/device.png)

# Methods to use
  ```setWithTitleBar``` set if with or without status bar.<br/>
  ```onLayoutCreate``` method you should override to manage views positions.<br/>
  ```setTextSize``` change text size of the buttons, textview, and editText.<br/>
  ```setViewWidthHeight``` change view width and height.<br/>
  ```setMargin``` set margin for a view.<br/>
  ```topRight``` set position of view x grid Units from top and right.<br/>
  ```rightBottom``` set position of view x grid Units from right and bottom.<br/>
  ```leftTop``` set position of view x grid Units from left and top.<br/>
  ```leftBottom``` set position of view x grid Units from left and bottom.<br/>
  

# How to use
Just extend Screen class from grid-android-designer, and override the ```onLayoutCreate``` method.
In ```onLayoutCreate```, you can change size, and adjust layouts.

    @Override
    public void onLayoutCreate() {
        super.onLayoutCreate();
    
        setViewWidthHeight(R.id.button1, 3.9f, 1.4f); // 3.9 Grid unit for width, and 1.4 grid units for height.
        setViewWidthHeight(R.id.button2, 3.9f, 1.4f);
        setViewWidthHeight(R.id.button3, 3.9f, 1.4f);
        setViewWidthHeight(R.id.button4, 3.9f, 1.4f);
        
        //change text size also.
        setTextSize(R.id.button1, SP * 0.6f);
        setTextSize(R.id.button2, SP * 0.6f);
        setTextSize(R.id.button3, SP * 0.6f);
        setTextSize(R.id.button4, SP * 0.6f);
    
        leftTop(R.id.button1, 3.0f, 3.5f); // 3.0 grid units from left, and 3.5 grid units from top.
        topRight(R.id.button2, 5.75f, 1.6f);
        leftBottom(R.id.button3, 7.1f, 1.2f);
        rightBottom(R.id.button4, 2.5f, 0.5f);
    }

# Contribution
Please don't hesitate to collaborate and upgrade this library. Fork, and send pull requests.