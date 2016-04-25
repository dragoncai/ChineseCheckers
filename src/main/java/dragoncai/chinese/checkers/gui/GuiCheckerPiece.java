package dragoncai.chinese.checkers.gui;

import dragoncai.chinese.checkers.ICheckerPiece;
import dragoncai.chinese.checkers.TeamColor;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GuiCheckerPiece extends JButton {
    private ICheckerPiece checkerPiece;
    private static final Map<TeamColor, Color> colorMap = new HashMap() {
        @Override
        public Color get(Object key) {
            if (key instanceof TeamColor) {
                switch ((TeamColor)key) {
                    case BLACK :
                        return Color.BLACK;
                    case BLUE:
                        return Color.BLUE;
                    case GREEN:
                        return Color.GREEN;
                    case RED:
                        return Color.RED;
                    case WHITE:
                        return Color.WHITE;
                    case YELLOW:
                        return Color.YELLOW;
                    default:
                        return Color.GRAY;
                }
            }
            return null;
        }
    };


    public GuiCheckerPiece(ICheckerPiece checkerPiece) {
        super(checkerPiece.getTeamColor().name());
        setPreferredSize(new Dimension(25, 25));
        setForeground(colorMap.get(checkerPiece.getTeamColor()));
        setBackground(colorMap.get(checkerPiece.getTeamColor()));
        this.checkerPiece = checkerPiece;
    }

}
