# About this library

First of all, this is a work in progress. Second, it's a helper library for Android projects.

What this library intents to achieve is:

* Provide a foundation for multimedia Android projects, i.e. games
* Nothing more.

## The object cache

To avoid the problems that arise with rapid object creation and the garbage collection (for instance in the context of
scene graphs where rapid matrix and vector multiplication is needed) a caching mini-framework is provided.

It consists of a thread local object cache that creates objects on the fly if needed and recycles existing objects
where it can.

### Integrate the cache

Create a singleton field:

```Java
    public static final IObjectCache<Matrix4> Cache = new ThreadLocalObjectCache<Matrix4>(new ObjectFactory<Matrix4>() {
        @NotNull @Override
        public Matrix4 createNew() {
            return new Matrix4();
        }
    });
```

and maybe some convenience methods:

```Java
    @NotNull
    public static Matrix4 createNew() {
        Matrix4 matrix = Cache.getOrCreate(); // this instance may be uninitialized, i.e. dirty
        return matrix.toUnit();               // clean it up
    }

    public static void recycle(@NotNull Matrix4 matrix) {
        Cache.registerElement(matrix);
    }

    public void recycle() {
        Cache.registerElement(this);
    }
```

### Request an object from the cache

```Java
    Matrix4 officialWay = Matrix4.Cache.getOrCreate();
    Matrix4 convenientWay = Matrix4.createNew();
```

### When done, recycle the object

```Java
    // official way
    Matrix4.Cache.recycle(matrix);

    // convenient way
    matrix.recycle();
```

As soon as the object is recycled, it can be reused.