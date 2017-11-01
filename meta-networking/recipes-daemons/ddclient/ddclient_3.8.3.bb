SECTION = "net/misc"
DESCRIPTION = "Ddclient is a Perl client used to update dynamic DNS entries for accounts on Dynamic DNS Network Services"
HOMEPAGE = "http://ddclient.sourceforge.net/"
LICENSE = "GPLv2"

SRC_URI = "\
    ${SOURCEFORGE_MIRROR}/ddclient/ddclient-${PV}.tar.bz2 \
    file://ip-up"

LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3"

RDEPENDS_${PN} = "\
    perl-module-getopt-long \
    perl-module-sys-hostname \
    perl-module-io-socket \
    perl-module-vars \
    perl-module-overload \
    perl-module-overloading \
    perl-module-config \
    perl-module-integer \
    perl-module-exporter-heavy \
    perl-module-symbol \
    perl-module-selectsaver \
    bash \
    perl \
    libio-socket-ssl-perl"

do_install() {
    install -d ${D}${sbindir} ${D}${sysconfdir}/ddclient ${D}${sysconfdir}/init.d ${D}${sysconfdir}/default/persistent
    install -d ${D}${sysconfdir}/ppp/ip-up.d/ install -d ${D}${docdir}/ddclient
    install -m 755 ${S}/ddclient ${D}${sbindir}
    install ${S}/sample-etc_ddclient.conf ${D}${sysconfdir}/ddclient/ddclient.conf
    install -m 755 ${WORKDIR}/ip-up ${D}${sysconfdir}/ppp/ip-up.d/ddclient
    install -m 0644 ${WORKDIR}/persistent ${D}/${sysconfdir}/default/persistent/50_ddclient
    sed -e 's|/etc/ddclient.conf|/etc/ddclient/ddclient.conf|g' ${S}/sample-etc_rc.d_init.d_ddclient > ${S}/rc_init
    install -m 755 ${S}/rc_init ${D}${sysconfdir}/init.d/ddclient
    install ${S}/README* ${D}${docdir}/ddclient
    install ${S}/COPY* ${D}${docdir}/ddclient
    install ${S}/sample* ${D}${docdir}/ddclient
}

CONFFILES_${PN} = "${sysconfdir}/ddclient/ddclient.conf"

SRC_URI[md5sum] = "3b426ae52d509e463b42eeb08fb89e0b"
SRC_URI[sha256sum] = "d40e2f1fd3f4bff386d27bbdf4b8645199b1995d27605a886b8c71e44d819591"
