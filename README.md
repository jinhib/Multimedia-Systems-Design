# Multimedia-Systems-Design


This project gains a practical understanding of Resampling and Filtering in the spatial and temporal domain. 
It consists of two parts, the first one aimed to develop understanding of sampling/aliasing issues in the spatial domain and the second one deals with sampling/aliasing issues in the temporal domain.

ImageDisplay.java
In my program you will need to display two images side by side (in the same or two different windows) –
1. your original image displayed on the left – This is an image of size 512x512 that you will create based on the criteria explained below.
2. Your processed output image displayed on the right – This image is the output of your algorithms on the original image above to create a resampled image depending on parameters explained below.

VedioDisplay.java
In my program you will need to display two videos side by side –
1. Your original video displayed on the left – This is video of size 512x512 that you
will create based on the criteria explained below. This is radial pattern just as in part 1, but it is also rotating clockwise at a certain specified speed. The creation and updating of your image at the respective times should simulate a rotating wheel.
2. Your processed output video displayed on the right – The output video is also of size 512x512 but in order to simulate temporal aliasing effects it will be given an fps rate of display, which means your output will be updated at specific times.

Extracredit.java
Change VedioDisplay of your assignment to take in two additional parameters –
1. The fourth parameter will be a boolean value (0 or 1) suggesting whether or not
you want to deal with aliasing. A 0 signifies do nothing (temporal aliasing will remain in your output). A value 1 signifies that temporal anti-aliasing should be
performed – you need to design a method to decrease temporal aliasing that
shows better output videos.
2. The fifth parameter s2 will be a scale factor that scales the input video down by a
factor. This is a floating point number eg – s=2.0 will scale the video down to 256x256. Note s need not be a complete integer. Also if the fourth parameter above is a 1, then you need to perform spatial antialiasing (like part1) along with temporal antialiasing.
Together with these two parameters you should be able to create scaled videos of your input at different frame rates and simultaneously minimize any aliasing effects due to resampling temporarily and spatially.
