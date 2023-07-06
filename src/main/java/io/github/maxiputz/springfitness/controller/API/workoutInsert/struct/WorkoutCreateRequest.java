package io.github.maxiputz.springfitness.controller.API.workoutInsert.struct;

import lombok.Getter;

@Getter
public class WorkoutCreateRequest {
    private String header;
    private String id;
    private String csv;

    WorkoutCreateRequest(){}
}
