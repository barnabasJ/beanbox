package net.jovacorp.bmj.beanbox.bean;

import lombok.Getter;
import net.jovacorp.bmj.beanbox.Coordinate;
import net.jovacorp.bmj.beanbox.Coordinates;
import net.jovacorp.bmj.beanbox.event.Event;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CalcDeviation
    extends AbstractProcessBean<Coordinates, Event<Coordinates>, Coordinates> {
  @Getter public String controlFilePath = "";
  public Pattern coordinatePattern = Pattern.compile("\\((\\d+)\\,(\\d+)\\)");

  public void setControlFilePath(String controlFilePath) {
    this.controlFilePath = controlFilePath;
  }

  private Coordinate calculateDeviation(
      Coordinate expectedCoordinate, Coordinate actualCoordinate) {
    int xDistance = expectedCoordinate.getX() - actualCoordinate.getX();
    int yDistance = expectedCoordinate.getY() - actualCoordinate.getY();
    return new Coordinate(xDistance, yDistance);
  }

  private Coordinate getNextCoordinate(Matcher matcher) {
    if (!matcher.find()) return null;
    int x = Integer.parseInt(matcher.group(1));
    int y = Integer.parseInt(matcher.group(2));
    return new Coordinate(x, y);
  }

  private String readFile(FileReader fr) throws IOException {
    StringBuilder buffer = new StringBuilder();
    int ch;
    while ((ch = fr.read()) != -1) {
      buffer.append((char) ch);
    }
    return buffer.toString();
  }

  @Override
  protected Coordinates processData(Coordinates inputData) {
    try (FileReader fr = new FileReader(controlFilePath)) {
      String fileContent = readFile(fr);
      Matcher matcher = coordinatePattern.matcher(fileContent);

      return new Coordinates(
          inputData.getCoordinates().stream()
              .map(
                  actualCoordinate -> {
                    Coordinate expectedCoordinate = getNextCoordinate(matcher);
                    if (expectedCoordinate == null) {
                      throw new IllegalStateException(
                          "Not enough expected coordinates in control file");
                    }

                    return calculateDeviation(expectedCoordinate, actualCoordinate);
                  })
              .collect(Collectors.toList()));

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
