package io.github.maxiputz.springfitness.database.Entity.Metadata;

import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Table(name = "metadata")
@Entity
public class Metadata {

    public Metadata () {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String workoutId;
    private String sport;
    private String startTime;
    private float averagePower;
    private float averageSpeed;
    private float averageHearRate;
    private float totalTime;
    private float totalDistance;

    private int count;

    @Column(columnDefinition = "TEXT")
    private String WorkoutDataCSV;


    public Metadata metadataWithoutCSV() {
        return new Metadata(id, workoutId, sport, startTime, averagePower, averageSpeed, averageHearRate, totalTime, totalDistance, count, "");
    }

    public Metadata(String workoutId, String sport, String startTime, float averagePower, float averageSpeed,
            float averageHearRate, float totalTime, float totalDistance) {
        this.workoutId = workoutId;
        this.sport = sport;
        this.startTime = startTime;
        this.averagePower = averagePower;
        this.averageSpeed = averageSpeed;
        this.averageHearRate = averageHearRate;
        this.totalTime = totalTime;
        this.totalDistance = totalDistance;

    }
}
