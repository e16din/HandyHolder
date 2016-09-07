# HandyHolder
[![Release](https://jitpack.io/v/e16din/HandyHolder.svg)](https://jitpack.io/#e16din/HandyHolder)

Handy view holder for RecyclerView.Adapter.

I created it to use with [SimpleRecycler](https://github.com/e16din/SimpleRecycler). 
You may use it with any RecyclerView.Adapter.

## Out of the box:
* Layout inflation
* Asynchronous layout inflation
* Ripple-effect to view
* OnClickListener and handy OnViewsClickListener


## Usage
```java
	HandyHolder.init(this); // set application context

	//.. in the adapter:

    @Override
    public int getItemViewType(int position) {
        if (condition1) return TYPE_FIRST;
        
        return TYPE_SECOND;
    }

    @Override
    public HandyHolder onCreateViewHolder(ViewGroup vParent, int viewType) {
    	if (viewType == TYPE_FIRST){
        	return HandyHolder.<String>create(this, vParent)
                	.layoutId(R.layout.item_first)
                	.async(true)
                	.clickListener(new OnClickListener<String>() {
                    	@Override
                    	public void onClick(String item, int position) {
                        	remove(position);
                    	}
                	})
                	.listener(new Listener())
                	.init();
        } else {
			return HandyHolder.<String>create(this, vParent)
                	.layoutId(R.layout.item_second)
                	.init();
        }
    }

    private static class Listener extends HandyListener<String> {
        TextView vItemText;

        @Override
        public void onInit(HandyHolder<String> h, View v) {
            vItemText = (TextView) v.findViewById(R.id.vItemText);
        }

        @Override
        public void onPreBind(HandyHolder<String> h, String item, int position) {
            super.onPreBind(h, item, position);

            h.rippleEffect(MathUtils.isEven(h.getAdapterPosition()));
        }

        @Override
        public void onBind(String item, int position) {
            vItemText.setText(position + ". " + item);
        }
    }
```

## License MIT
Copyright (c) 2016 [Александр Кундрюков (e16din)](http://goo.gl/pzjc8x)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
