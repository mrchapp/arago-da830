DESCRIPTION = "DSP based gstreamer  plugins"

PR = "r6"

RDEPENDS_${PN} = " \
       gstreamer \
       gst-plugins-base \
       gst-plugins-good \
       gst-plugins-bad \
       gst-plugins-ugly \
       gst-plugin-typefindfunctions \
       gst-plugin-queue2 \
       gst-plugin-ossaudio \
       gst-plugin-alsa \
       gst-plugin-audioconvert \
       gst-plugin-audioresample \
       gst-plugin-volume \
       gst-plugin-audiotestsrc \
       gst-plugin-videotestsrc \
       gst-plugin-video4linux2 \
       gst-plugin-videoscale \
       gst-plugin-videorate \
       gst-plugin-videomixer \
       gst-plugin-videoflip \
       gst-plugin-ffmpegcolorspace \
       gst-plugin-decodebin \
       gst-plugin-playbin \
       gst-plugin-autodetect \
       gst-plugin-rtp \
       gst-plugin-udp \
       gst-plugin-rtpmanager \
       gst-plugin-tcp \
       gst-plugin-avi \
       gst-plugin-qtdemux \
       gst-plugin-mad \
       gstreamer-ti \
       gstreamer-ti-demo-script \
"

inherit task

