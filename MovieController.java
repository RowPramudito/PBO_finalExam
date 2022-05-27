package moviereview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class MovieController {
    MovieModel movieModel;
    MovieView movieView;
    String movie_title;

    
    public MovieController(MovieModel movieModel, MovieView movieView) {
        this.movieModel = movieModel;
        this.movieView = movieView;
        

        String movieData[][] = movieModel.readData();
        movieView.table.setModel((new JTable(movieData, movieView.columnName)).getModel());

        movieView.table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = movieView.table.getSelectedRow();
                int col = movieView.table.getSelectedColumn();
                
                String movie_title = movieView.table.getValueAt(row, 0).toString();
                System.out.println("data : " + movie_title);
                setMovieTitle(movie_title);
                System.out.println("data : " + getMovieTitle());
            }
        });

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
                movieModel.deleteData(getMovieTitle());;
            }
            
        });
    }

    void setMovieTitle(String movieTitle) {this.movie_title = movieTitle;}
    String getMovieTitle() {return this.movie_title;}
}
