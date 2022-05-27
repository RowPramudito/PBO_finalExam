package moviereview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class MovieController {
    MovieModel movieModel;
    MovieView movieView;
    
    public MovieController(MovieModel movieModel, MovieView movieView) {
        this.movieModel = movieModel;
        this.movieView = movieView;

        String movieData[][] = movieModel.readData();
        movieView.table.setModel((new JTable(movieData, movieView.columnName)).getModel());

        movieView.btnAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String judul = movieView.getJudul(); 
                String alur = movieView.getAlur(); double alurSc = Double.parseDouble(alur);
                String penokohan = movieView.getPenokohan(); double penokohanSc = Double.parseDouble(penokohan);
                String akting = movieView.getAkting(); double aktingSc = Double.parseDouble(akting);
                
                double scoreSc = (alurSc + penokohanSc + aktingSc) / 3;

                movieModel.insertData(judul, alurSc, penokohanSc, aktingSc, scoreSc);
            }
            
        });

        movieView.btnUpdate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String judul = movieView.getJudul(); 
                String alur = movieView.getAlur(); double alurSc = Double.parseDouble(alur);
                String penokohan = movieView.getPenokohan(); double penokohanSc = Double.parseDouble(penokohan);
                String akting = movieView.getAkting(); double aktingSc = Double.parseDouble(akting);
                
                double scoreSc = (alurSc + penokohanSc + aktingSc) / 3;

                movieModel.updateData(judul, alurSc, penokohanSc, aktingSc, scoreSc);
            }
            
        });

        movieView.btnReset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String movieData[][] = movieModel.readData();
                movieView.table.setModel((new JTable(movieData, movieView.columnName)).getModel());
            }
            
        });

        movieView.btnDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                
            }
            
        });
    }
}
