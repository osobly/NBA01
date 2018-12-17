package tsp.lucas.nba01;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Game extends ArrayList<Parcelable> implements Parcelable {
	private int id;
	private String date;
	private String Hour;
	private String HomeTeam;
	private String AwayTeam;
	private int HTscore;
	private int ATscore;
	private String Ytid;
	
	public Game(int id, String date, String Hour, String HomeTeam, String AwayTeam) {
		this.id = id;
		this.date = date;
		this.Hour = Hour;
		this.HomeTeam = HomeTeam;
		this.AwayTeam =AwayTeam;
	}

	public String getYtid() {
		return Ytid;
	}

	public void setYtid(String ytid) {
		Ytid = ytid;
	}

	public int getId() {
		return id;
	}

	public void setscores(int ATscore, int HTscore) {
		this.HTscore = HTscore;
		this.ATscore = ATscore;
	}

	public int[] getscores() {
		int[] scores = new int[2];
		scores[0] = ATscore;
		scores[1] = HTscore;
		return scores;
	}
	
	public String getdate( ) {
		return this.date;
	}
	
	public String getHour( ) {
		return this.Hour;
	}
	
	public String getHomeTeam( ) {
		return this.HomeTeam;
	}
	
	public String getAwayTeam( ) {
		return this.AwayTeam;
	}
	
	public String toString() {
		String retour = "Le " + this.date +" à " + this.Hour + " : " + this.AwayTeam + " @ " + this.HomeTeam ;
		return retour;
	}

	protected Game(Parcel in) {
		id = in.readInt();
		date = in.readString();
		Hour = in.readString();
		HomeTeam = in.readString();
		AwayTeam = in.readString();
		HTscore = in.readInt();
		ATscore = in.readInt();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(date);
		dest.writeString(Hour);
		dest.writeString(HomeTeam);
		dest.writeString(AwayTeam);
		dest.writeInt(HTscore);
		dest.writeInt(ATscore);
	}

	@SuppressWarnings("unused")
	public static final Parcelable.Creator<Game> CREATOR = new Parcelable.Creator<Game>() {
		@Override
		public Game createFromParcel(Parcel in) {
			return new Game(in);
		}

		@Override
		public Game[] newArray(int size) {
			return new Game[size];
		}
	};
}
