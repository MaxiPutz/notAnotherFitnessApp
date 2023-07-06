package io.github.maxiputz.springfitness.controller.API.workoutInsert.struct;

import lombok.Getter;

@Getter
public class MetadataCreateRequest {
    private String header;
    private String csv;

    public MetadataCreateRequest(){}
}
