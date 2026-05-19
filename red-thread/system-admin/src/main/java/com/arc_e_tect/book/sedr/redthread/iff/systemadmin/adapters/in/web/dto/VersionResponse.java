package com.arc_e_tect.book.sedr.redthread.iff.systemadmin.adapters.in.web.dto;

import lombok.Builder;
import lombok.Value;

import java.util.Map;

@Value
@Builder
public class VersionResponse {
    String versionName;
    double versionCode;
    Map<String, DependencyVersionDto> dependencies;
}
