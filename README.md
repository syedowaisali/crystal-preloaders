[ ![Download](https://api.bintray.com/packages/syedowaisali/maven/crystalpreloaders/images/download.svg) ](https://bintray.com/syedowaisali/maven/crystalpreloaders/_latestVersion)

# Crystal Preloaders

A stylish design preloaders.

![alt tag](https://drive.google.com/uc?export=view&id=0B9bDENyIABT6R04zNDlTelc0Q2M)

# Usage
Add a dependency to your `build.gradle`:
```groovy
dependencies {
    compile 'com.crystal:crystalpreloaders:1.0.0'
}
```

# Sample usage

Add the CrystalPreloader to your layout:

```groovy
<com.crystal.crystalpreloaders.widgets.CrystalPreloader
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:crs_pl_style="skype_balls"
    app:crs_pl_size="small"
    app:crs_pl_fg_color="#000"
    app:crs_pl_bg_color="#fff"/>
```

# Preloaders

Attribute | Default | Options | Description
------------ | ------------- | ------------- | -------------
crs_pl_size | small | ``very_small``, ``small``, ``medium``, ``large``, ``extra_large`` | Apply different  sizes.
crs_pl_fg_color| #000000 | any color | Apply foreground color.
crs_pl_bg_color | #ffffff | any color | Apply background color.