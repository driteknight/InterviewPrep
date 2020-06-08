package Robot;

class Grid {

    private int rowLength;
    private int columnLength;

    private Cell[][] grid;


    public Grid(int[][] gridArray) {

        validGridArray(gridArray);

        this.rowLength = gridArray.length;
        this.columnLength = gridArray[0].length;

        this.grid = new Cell[this.rowLength][this.columnLength];
        populateGrid(gridArray);
    }

    private void validGridArray(int[][] gridArray) {
        if (gridArray == null || gridArray.length < 1 || gridArray[0].length < 1) {
            throw new RuntimeException("Null Grid Array or Invalid Grid Array Length");
        }

        if (gridArray[0][0] == 1) {
            throw new RuntimeException("Robot Start value cannot be a Obstacle");
        }

        if (gridArray[gridArray.length - 1][gridArray[0].length - 1] == 1) {
            throw new RuntimeException("Robot End value cannot be a Obstacle");
        }
    }

    private void populateGrid(int[][] gridArray) {
        for (int i = 0; i < this.rowLength; i++) {
            for (int j = 0; j < this.columnLength; j++) {
                grid[i][j] = Cell.valueToCell(gridArray[i][j]);
            }
        }
    }

    public boolean isCellObstacle(int rowIndex, int colIndex) {
        validateIndices(rowIndex, colIndex);
        return grid[rowIndex][colIndex] == Cell.OBSTACLE;
    }

    public boolean isCellEmpty(int rowIndex, int colIndex) {
        validateIndices(rowIndex, colIndex);
        return grid[rowIndex][colIndex] == Cell.EMPTY;
    }

    private void validateIndices(int rowIndex, int colIndex) {
        if (rowIndex < 0 || rowIndex >= rowLength) {
            throw new RuntimeException(String.format("Invalid Row Index: %s. RowLength: %s", rowIndex, rowLength));
        }

        if (colIndex < 0 || colIndex >= columnLength) {
            throw new RuntimeException(String.format("Invalid Column Index: %s. ColumnLength: %s", colIndex, columnLength));
        }
    }

    public int getRowLength() {
        return rowLength;
    }

    public void setRowLength(int rowLength) {
        this.rowLength = rowLength;
    }

    public int getColumnLength() {
        return columnLength;
    }

    public void setColumnLength(int columnLength) {
        this.columnLength = columnLength;
    }


    private enum Cell {
        OBSTACLE(1),
        EMPTY(0);

        int value;

        Cell(int value) {
            this.value = value;
        }

        public static Cell valueToCell(int value) {
            for (Cell cell : Cell.values()) {
                if (value == cell.value) {
                    return cell;
                }
            }

            throw new RuntimeException("Invalid Cell Value: " + value);
        }
    }
}

class UniquePath {
    public int findUniquePaths(int[][] gridArray) {
        Grid grid = new Grid(gridArray);
        int[][] uniquePathMatrix = generateUniquePathMatrix(grid);
        return uniquePathMatrix[grid.getRowLength()][grid.getColumnLength()];
    }

    private int[][] generateUniquePathMatrix(Grid grid) {
        int[][] uniquePathMatrix = new int[grid.getRowLength() + 1][grid.getColumnLength() + 1];

        // Start Position of the bot.
        uniquePathMatrix[1][1] = 1;

        for (int i = 1; i < uniquePathMatrix.length; i++) {
            for (int j = 1; j < uniquePathMatrix[0].length; j++) {
                if (i == 1 && i == j) {
                    continue;
                } else if (grid.isCellObstacle(i - 1, j - 1)) {
                    uniquePathMatrix[i][j] = 0;
                } else {
                    uniquePathMatrix[i][j] = uniquePathMatrix[i - 1][j] + uniquePathMatrix[i][j - 1];
                }
            }
        }


        return uniquePathMatrix;
    }
}




