package com.jpmc.theater.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * An util to print the showing schedule in a tabular format on console/command line
 */
public class ConsoleTable {
    private static final String HORIZONTAL_SEPARATOR = "-";
    private String verticalSeparator;
    private String joinSeparator;
    private String[] headers;
    private final List<String[]> rows = new ArrayList<>();
    private boolean rightAlign;

    public ConsoleTable() {
	setShowVerticalLines(false);
    }

    @SuppressWarnings("unused") public void setRightAlign(boolean rightAlign) {
	this.rightAlign = rightAlign;
    }

    public void setShowVerticalLines(boolean showVerticalLines) {
	verticalSeparator = showVerticalLines ? "|" : "";
	joinSeparator = showVerticalLines ? "+" : " ";
    }

    public void setHeaders(String... headers) {
	this.headers = headers;
    }

    public void addRow(String... cells) {
	rows.add(cells);
    }

    public void print() {
	int[] maxWidths = headers != null ? Arrays.stream(headers).mapToInt(String::length).toArray() : null;

	for (String[] cells : rows) {
	    if (maxWidths == null) {
		maxWidths = new int[cells.length];
	    }
	    if (cells.length != maxWidths.length) {
		throw new IllegalArgumentException("Number of row-cells and headers should be consistent");
	    }
	    for (int i = 0; i < cells.length; i++) {
		maxWidths[i] = Math.max(maxWidths[i], cells[i].length());
	    }
	}

	if (headers != null) {
	    printLine(maxWidths);
	    printRow(headers, maxWidths);
	    printLine(maxWidths);
	}
	for (String[] cells : rows) {
	    printRow(cells, maxWidths);
	}
	if (headers != null) {
	    printLine(maxWidths);
	}
    }

    private void printLine(int[] columnWidths) {
	for (int i = 0; i < columnWidths.length; i++) {
	    String line = String.join("", Collections.nCopies(columnWidths[i] + verticalSeparator.length() + 1,
			    HORIZONTAL_SEPARATOR));
	    System.out.print(joinSeparator + line + (i == columnWidths.length - 1 ? joinSeparator : ""));
	}
	System.out.println();
    }

    private void printRow(String[] cells, int[] maxWidths) {
	for (int i = 0; i < cells.length; i++) {
	    String s = cells[i];
	    String verStrTemp = i == cells.length - 1 ? verticalSeparator : "";
	    if (rightAlign) {
		System.out.printf("%s %" + maxWidths[i] + "s %s", verticalSeparator, s, verStrTemp);
	    } else {
		System.out.printf("%s %-" + maxWidths[i] + "s %s", verticalSeparator, s, verStrTemp);
	    }
	}
	System.out.println();
    }
}
