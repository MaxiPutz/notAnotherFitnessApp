package io.github.maxiputz.springfitness.CSVManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.github.maxiputz.springfitness.database.Entity.Metadata.Metadata;

public class CSVManager {
    private static String headeStringMetadata = "id,sport,startTime,averagePower,averageSpeed,averageHearRate,totalTime,totalDistance";
    private static String headeStringWorkout = "id,lat,long,timerTime,power,speed,heartRate";

    public static List<Metadata> toMetadataArr(String headeString, String csv) {
        var _header = headeString.split(",");
        var _csv = Arrays.asList(csv.split(("\n"))).stream().map(ele -> ele.split((","))).toList();

        var arr = new ArrayList<Metadata>();

        for (int i = 0; i < _csv.toArray().length; i++) {
            final var row = _csv.get(i);
            String id = "";
            String sport = "";
            String startTime = "";
            float averagePower = -1;
            float averageSpeed = -1;
            float averageHearRate = -1;
            float totalTime = -1;
            float totalDistance = -1;

            for (int j = 0; j < row.length; j++) {
                switch (_header[j]) {
                    case "id":
                        id = row[j];
                        break;
                    case "sport":
                        sport = row[j];
                        break;
                    case "startTime":
                        startTime = row[j];
                        break;
                    case "averagePower":
                        try {
                            averagePower = Float.parseFloat(row[j]);
                        } catch (NumberFormatException e) {
                            averagePower = -1;
                        }
                        break;
                    case "averageSpeed":
                        try {
                            averageSpeed = Float.parseFloat(row[j]);
                        } catch (NumberFormatException e) {
                            averageSpeed = -1;
                        }
                        break;
                    case "averageHearRate":
                        try {
                            averageHearRate = Float.parseFloat(row[j]);
                        } catch (NumberFormatException e) {
                            averageHearRate = -1;
                        }
                        break;
                    case "totalTime":
                        try {
                            totalTime = Float.parseFloat(row[j]);
                        } catch (NumberFormatException e) {
                            totalTime = -1;
                        }
                        break;
                    case "totalDistance":
                        try {
                            totalDistance = Float.parseFloat(row[j]);
                        } catch (NumberFormatException e) {
                            totalDistance = -1;
                        }
                        break;

                }
            }

            var tmp = new Metadata(id, sport, startTime, averagePower, averageSpeed, averageHearRate, totalTime,
                    totalDistance);
            arr.add(tmp);
            // arr.add(new Metadata(id, sport, startTime, averagePower, averageSpeed,
            // averageHearRate, totalTime,
            // totalDistance));

        }
        return arr;
    }

    public static String fromMetadataArr(String headeString, List<Metadata> metadataList) {
        StringBuilder csvBuilder = new StringBuilder(headeString + "\n");

        for (Metadata metadata : metadataList) {
            csvBuilder.append(metadata.getId()).append(",");
            csvBuilder.append(metadata.getSport()).append(",");
            csvBuilder.append(metadata.getStartTime()).append(",");
            csvBuilder.append(metadata.getAveragePower()).append(",");
            csvBuilder.append(metadata.getAverageSpeed()).append(",");
            csvBuilder.append(metadata.getAverageHearRate()).append(",");
            csvBuilder.append(metadata.getTotalTime()).append(",");
            csvBuilder.append(metadata.getTotalDistance()).append("\n");
        }

        return csvBuilder.toString();
    }

}
