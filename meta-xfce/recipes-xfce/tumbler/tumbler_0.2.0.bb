DESCRIPTION="Thumbnail service implementing the thumbnail management D-Bus specification"
SECTION = "x11/libs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
DEPENDS = "dbus-glib dbus-glib-native freetype gdk-pixbuf poppler curl xfce4-dev-tools-native libxml2 libgsf"

inherit xfce gtk-doc

SRC_URI[md5sum] = "dd5f9bae6a2470eb5fff0dc9edd3ea09"
SRC_URI[sha256sum] = "4e27a59694b0a5cc69ebccbdb00c724e670b5b7c30bc4dc0b461aac93f234fac"
SRC_URI += "file://0001-configure-use-pkg-config-for-freetype2.patch"

INSANE_SKIP_${PN} = "dev-so"

PACKAGECONFIG ??= ""
PACKAGECONFIG[gstreamer-thumbnailer] = "--enable-gstreamer-thumbnailer,--disable-gstreamer-thumbnailer,gstreamer1.0 gstreamer1.0-plugins-base"

FILES_${PN} += "${datadir}/dbus-1/services \
                ${libdir}/tumbler-1/tumblerd \
                ${libdir}/tumbler-1/plugins/*.so \
                ${libdir}/tumbler-1/plugins/cache/*.so \
"

FILES_${PN}-dev += "${libdir}/tumbler-1/plugins/*.la \
                    ${libdir}/tumbler-1/plugins/cache/*.la"
FILES_${PN}-dbg += "${libdir}/tumbler-1/.debug \
                    ${libdir}/tumbler-1/plugins/.debug \
                    ${libdir}/tumbler-1/plugins/cache/.debug \
"
