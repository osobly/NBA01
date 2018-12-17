package tsp.lucas.nba01;

import android.os.Parcel;
import android.os.Parcelable;

public class Team implements Parcelable{
    private int id;
    private String name;
    private int wins;
    private int losses;
    private double winpercentage;

    public Team(int id, String name, int wins, int losses, double winpercentage) {
        this.id = id;
        this.name = name;
        this.wins = wins;
        this.losses = losses;
        this.winpercentage = winpercentage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public double getWinpercentage() {
        return winpercentage;
    }

    public void setWinpercentage(double winpercentage) {
        this.winpercentage = winpercentage;
    }

    protected Team(Parcel in) {
        id = in.readInt();
        name = in.readString();
        wins = in.readInt();
        losses = in.readInt();
        winpercentage = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(wins);
        dest.writeInt(losses);
        dest.writeDouble(winpercentage);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Team> CREATOR = new Parcelable.Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel in) {
            return new Team(in);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };
}
