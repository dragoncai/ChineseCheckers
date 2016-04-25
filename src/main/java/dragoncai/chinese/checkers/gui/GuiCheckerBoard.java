package dragoncai.chinese.checkers.gui;

import dragoncai.chinese.checkers.*;

import javax.swing.*;
import java.util.Collection;

public class GuiCheckerBoard extends JPanel{

    public GuiCheckerBoard() {
        super(true);
        setLayout(null);
        CheckerBoard checkerBoard = new CheckerBoard();
        checkerBoard.init();

        Collection<ICheckerPiece> values = checkerBoard.getCheckerPieceMap().values();
        for (ICheckerPiece value : values) {
            addPiece(value);
        }
        setVisible(true);

    }

    private void addPiece(ICheckerPiece checkerPiece) {
        if (checkerPiece.getTeamColor() == TeamColor.FRONTIER) {
            return;
        }
        GuiCheckerPiece guiCheckerPiece = new GuiCheckerPiece(checkerPiece);
        add(guiCheckerPiece);
        CartesianPosition cartesianPosition = new CartesianPosition(checkerPiece.getPosition());
        guiCheckerPiece.setBounds(cartesianPosition.getX(), cartesianPosition.getY(), guiCheckerPiece.getPreferredSize().width, guiCheckerPiece.getPreferredSize().height);
        guiCheckerPiece.repaint();
    }
}
