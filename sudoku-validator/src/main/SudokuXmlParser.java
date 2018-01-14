package main;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Class to parse input XML file to extract Sudoku board information.
 *
 * Created by sofia on 10/30/15.
 */
public class SudokuXmlParser {

    /**
     * Method to extract to Sudoku board information from given input XML file.
     *
     * @param filePath path to input XML file
     * @return int matrix containing Sudoku board
     */
    public int[][] extractSudokuBoardInfo(String filePath) {
        int[][] output = new int[SudokuValidator.SIZE][SudokuValidator.SIZE];

        try {
            File file = new File(filePath);

            DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = docBuilder.parse(file);

            if (doc.hasChildNodes()) {
                NodeList nodeList = doc.getChildNodes();
                Node tableNode = nodeList.item(0);
                NodeList rowNodes = tableNode.getChildNodes();

                // for each row, extract row ID
                for (int i = 0; i < rowNodes.getLength(); i++) {

                    if (Node.ELEMENT_NODE == rowNodes.item(i).getNodeType()) {
                        Node rowNode = rowNodes.item(i);
                        NamedNodeMap rowAttributeMap = rowNode.getAttributes();
                        Node rowAttributeNode = rowAttributeMap.item(0);

                        int rowId = Integer.parseInt(rowAttributeNode.getNodeValue());

                        NodeList columnNodes = rowNode.getChildNodes();

                        // for each column, extract column ID and value for the given [row][column] position
                        for (int j = 0; j < columnNodes.getLength(); j++) {

                            if (Node.ELEMENT_NODE == columnNodes.item(j).getNodeType()) {
                                Node columnNode = columnNodes.item(j);
                                NamedNodeMap columnAttributeMap = columnNode.getAttributes();
                                Node columnAttributeNode = columnAttributeMap.item(0);

                                int colId = Integer.parseInt(columnAttributeNode.getNodeValue());
                                int value = Integer.parseInt(columnNode.getTextContent());

                                output[rowId-1][colId-1] = value;
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output;
    }

}
