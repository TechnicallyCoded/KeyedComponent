# KeyedComponent

## Dependencies

Add the repository and dependency:

```kotlin
repositories {
    maven("https://repo.tcoded.com/releases")
}

dependencies {
    // Recommended
    compileOnly("com.tcoded:KeyedComponent:1.1.0")
    
    // Not recommended
    // If nothing else in your runtime already provides this dependency, you can add it as an implementation.
    // implementation("com.tcoded:KeyedComponent:VERSION")
}
```

## How to use

Java example:

```java
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.key.Key;
import com.tcoded.keyedcomponent.KeyedComponent;

// create from a string key
KeyedComponent kc = KeyedComponent.of("example", Component.text("Hello"));

// or create from an explicit Key
Key key = Key.key("namespace_here", "value_here");
KeyedComponent kc2 = KeyedComponent.of(key, Component.text("World"));

// getters
Key kcKey = kc.key();
Component kcComponent = kc.asComponent();
ComponentLike kcValue = kc.value();
```