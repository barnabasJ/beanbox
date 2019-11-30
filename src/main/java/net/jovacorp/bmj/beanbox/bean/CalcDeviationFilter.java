package net.jovacorp.bmj.beanbox.bean;

public class CalcDeviationFilter {
  /*
  @Setter public File controlFile;
  public Pattern coordinatePattern = Pattern.compile("\\((\\d+)\\,(\\d+)\\)");

  public CalcDeviationFilter(Writeable<List<Result>> output) {
    super(output);
  }

  public CalcDeviationFilter(Readable<ProcessImage> input) {
    super(input);
  }

  @Override
  protected List<Result> process(ProcessImage image) {
    if (controlFile == null) throw new IllegalStateException("controlFile can not be null");
    try (FileReader fr = new FileReader(controlFile); ) {
      String fileContent = readFile(fr);
      Matcher matcher = coordinatePattern.matcher(fileContent);

      return image.getCentroids().stream()
          .map(
              actualCoordinate -> {
                Coordinate expectedCoordinate = getNextCoordinate(matcher);
                if (expectedCoordinate == null) {
                  throw new IllegalStateException(
                      "Not enough expected coordinates in control file");
                }

                double deviation = calculateDeviation(expectedCoordinate, actualCoordinate);
                return new Result(
                    expectedCoordinate, actualCoordinate, image.getFaultTolerance(), deviation);
              })
          .collect(Collectors.toList());

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  private double calculateDeviation(Coordinate expectedCoordinate, Coordinate actualCoordinate) {
    int xDistance = expectedCoordinate.getX() - actualCoordinate.getX();
    int yDistance = expectedCoordinate.getY() - actualCoordinate.getY();
    return Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
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

     */
}
