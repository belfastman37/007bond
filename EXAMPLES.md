# Example Project Tutorial

## Creating Your First App with AppForge

This tutorial walks you through creating a simple "Hello World" app with a counter button.

## Step 1: Create the Project

1. Open AppForge
2. Tap "Create New Project"
3. Enter project details:
   - **Project Name**: `CounterApp`
   - **Package Name**: `com.example.counterapp`
4. Tap "Create"

The project opens automatically with MainActivity.java displayed.

## Step 2: Understanding the Default Code

You'll see this code:

```java
package com.example.counterapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        TextView textView = findViewById(R.id.textView);
        textView.setText("Hello from your app!");
    }
}
```

## Step 3: Modify MainActivity.java

Replace the default code with this enhanced version:

```java
package com.example.counterapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;

public class MainActivity extends Activity {
    
    private int counter = 0;
    private TextView textView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);
        
        updateDisplay();
        
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                updateDisplay();
            }
        });
    }
    
    private void updateDisplay() {
        textView.setText("Count: " + counter);
    }
}
```

**What we changed**:
- Added a `counter` variable
- Made `textView` a class field
- Added button click listener
- Created `updateDisplay()` method
- Increments counter on button click

## Step 4: Save Your Code

1. Tap the "Save" button (bottom of screen)
2. Or tap the save icon in the toolbar
3. Wait for "File saved" confirmation

## Step 5: Understanding the Layout

Now let's look at the layout file:

1. Tap the file menu (FAB in bottom-right)
2. Select "File Browser"
3. Find and open: `src/main/res/layout/activity_main.xml`

You'll see:

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    android:orientation="vertical"
    android:padding="16dp">

    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        android:textSize="24sp" />

    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:text="Click Me" />

</LinearLayout>
```

**The layout has**:
- A TextView (displays the counter)
- A Button (increments the counter)
- LinearLayout (arranges views vertically)

## Step 6: Customize the Layout (Optional)

You can modify the button text:

```xml
<Button
    android:id="@+id/button"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginTop="16dp"
    android:text="Add One" />
```

Save the file after editing.

## Step 7: Build the Project

1. Switch back to MainActivity.java
2. Tap the "Build" button
3. Wait for the build process
4. You'll see a success message

**Note**: Full compilation requires Android SDK. This demonstrates the build interface.

## What You've Learned 🎓

### Java Concepts
- Activity lifecycle
- findViewById
- OnClickListener
- Member variables
- Private methods

### Android Concepts
- Activity structure
- View IDs
- Layout XML
- Button handling
- TextView updates

## Next Steps 🚀

### Add More Features

**Reset Button**:
```java
// In activity_main.xml, add:
<Button
    android:id="@+id/resetButton"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginTop="8dp"
    android:text="Reset" />

// In MainActivity.java, add:
Button resetButton = findViewById(R.id.resetButton);
resetButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        counter = 0;
        updateDisplay();
    }
});
```

**Decrement Button**:
```java
// Add button in XML with id="decrementButton"
// Then add this listener:
decrementButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        counter--;
        updateDisplay();
    }
});
```

**Change Colors**:
```java
// Make counter red when negative, green when positive:
private void updateDisplay() {
    textView.setText("Count: " + counter);
    if (counter > 0) {
        textView.setTextColor(0xFF00FF00); // Green
    } else if (counter < 0) {
        textView.setTextColor(0xFFFF0000); // Red
    } else {
        textView.setTextColor(0xFF000000); // Black
    }
}
```

## More Example Projects

### 1. Temperature Converter

```java
// Convert Celsius to Fahrenheit
EditText celsiusInput = findViewById(R.id.celsiusInput);
Button convertButton = findViewById(R.id.convertButton);
TextView result = findViewById(R.id.result);

convertButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String input = celsiusInput.getText().toString();
        if (!input.isEmpty()) {
            double celsius = Double.parseDouble(input);
            double fahrenheit = (celsius * 9/5) + 32;
            result.setText(fahrenheit + "°F");
        }
    }
});
```

### 2. Random Number Generator

```java
import java.util.Random;

Random random = new Random();
Button generateButton = findViewById(R.id.generateButton);
TextView numberDisplay = findViewById(R.id.numberDisplay);

generateButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        int randomNumber = random.nextInt(100) + 1;
        numberDisplay.setText("Random: " + randomNumber);
    }
});
```

### 3. Simple Todo List

```java
import java.util.ArrayList;

ArrayList<String> todos = new ArrayList<>();
EditText todoInput = findViewById(R.id.todoInput);
Button addButton = findViewById(R.id.addButton);
TextView todoList = findViewById(R.id.todoList);

addButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String todo = todoInput.getText().toString();
        if (!todo.isEmpty()) {
            todos.add(todo);
            updateList();
            todoInput.setText("");
        }
    }
});

private void updateList() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < todos.size(); i++) {
        sb.append((i + 1) + ". " + todos.get(i) + "\n");
    }
    todoList.setText(sb.toString());
}
```

## Tips for Success 💡

1. **Save Frequently**: Use the save button often
2. **Test Small Changes**: Build after each feature
3. **Use Comments**: Explain your code
4. **Start Simple**: Don't try everything at once
5. **Learn Gradually**: Master basics before advanced features

## Common Mistakes to Avoid ❌

### 1. Forgetting to Save
Always save before building or switching files.

### 2. Wrong IDs
Make sure XML IDs match the ones in Java code:
```java
// XML: android:id="@+id/myButton"
// Java: findViewById(R.id.myButton)
```

### 3. Missing Imports
If you see errors, you might need imports:
```java
import android.widget.Button;
import android.widget.EditText;
```

### 4. Null Pointer Exceptions
Check that views exist in your layout:
```java
Button button = findViewById(R.id.button);
if (button != null) {
    button.setOnClickListener(...);
}
```

## Debugging Tips 🔍

### Code Not Working?
1. Check for typos
2. Verify IDs match
3. Ensure proper imports
4. Review Android lifecycle

### Build Fails?
1. Check syntax errors
2. Missing semicolons
3. Unclosed braces
4. Package name mismatch

## Resources for Learning 📚

### Java Basics
- Variables and data types
- Control flow (if/else)
- Loops (for/while)
- Methods and classes

### Android Basics
- Activity lifecycle
- View and ViewGroup
- Layouts (Linear, Relative)
- Event handling

### Where to Learn More
- Android Developer Documentation
- Java tutorials online
- YouTube Android tutorials
- Practice with AppForge!

## Challenge Projects 🏆

Try building these on your own:

### Easy
1. Tip Calculator
2. Age Calculator
3. Color Picker
4. Simple Quiz

### Medium
1. BMI Calculator
2. Stopwatch
3. Unit Converter
4. Note Taker

### Hard
1. Calculator
2. Expense Tracker
3. Weather Display
4. Mini Game

## Conclusion 🎉

You've created your first Android app with AppForge! You learned:
- Project creation
- Code editing
- Layout modification
- Event handling
- Building projects

Keep experimenting and building! The best way to learn is by doing.

**Happy Coding! 📱💻**
