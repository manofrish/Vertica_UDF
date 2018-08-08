/*
 * Copyright (c) 2014 - 2015 Hewlett Packard Enterprise Development LP
 *
 * Description: Support code for UDx subsystem
 *
 * Create Date: Mon Dec 11 15:27:27 2017
 */
/* Build-time identification for VerticaSDKJava */

package com.vertica.sdk;

public class BuildInfo {
    public static final String VERTICA_BUILD_ID_Brand_Name       = "Vertica Analytic Database";
    public static final String VERTICA_BUILD_ID_Brand_Version    = "v9.0.1-1";
    public static final String VERTICA_BUILD_ID_SDK_Version      = "9.0.1";
    public static final String VERTICA_BUILD_ID_Codename         = "Grader";
    public static final String VERTICA_BUILD_ID_Date             = "Mon Dec 11 15:27:27 2017";
    public static final String VERTICA_BUILD_ID_Machine          = "build-centos6";
    public static final String VERTICA_BUILD_ID_Branch           = "tag";
    public static final String VERTICA_BUILD_ID_Revision         = "releases/VER_9_0_RELEASE_BUILD_1_1_20171211";
    public static final String VERTICA_BUILD_ID_Checksum         = "f1a33db2d60f0f82dd7e0a919583c59e";

    public static VerticaBuildInfo get_vertica_build_info() {
        VerticaBuildInfo vbi = new VerticaBuildInfo();
        vbi.brand_name      = BuildInfo.VERTICA_BUILD_ID_Brand_Name;
        vbi.brand_version   = BuildInfo.VERTICA_BUILD_ID_Brand_Version;
        vbi.sdk_version     = BuildInfo.VERTICA_BUILD_ID_SDK_Version;
        vbi.codename        = BuildInfo.VERTICA_BUILD_ID_Codename;
        vbi.build_date      = BuildInfo.VERTICA_BUILD_ID_Date;
        vbi.build_machine   = BuildInfo.VERTICA_BUILD_ID_Machine;
        vbi.branch          = BuildInfo.VERTICA_BUILD_ID_Branch;
        vbi.revision        = BuildInfo.VERTICA_BUILD_ID_Revision;
        vbi.checksum        = BuildInfo.VERTICA_BUILD_ID_Checksum;
        return vbi;
    }
}
/* end of this file */
