package tsp.lucas.nba01;

import android.os.Parcel;
import android.os.Parcelable;

public class Player implements Parcelable{
    private String name;
    private float fgpct;
    private float reb;
    private float ast;
    private float stl;
    private float blk;
    private float pts;

    public Player(String name, float fgpct, float reb, float ast, float stl, float blk, float pts) {
        this.name = name;
        this.fgpct = fgpct;
        this.reb = reb;
        this.ast = ast;
        this.stl = stl;
        this.blk = blk;
        this.pts = pts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getFgpct() {
        return fgpct;
    }

    public void setFgpct(float fgpct) {
        this.fgpct = fgpct;
    }

    public float getReb() {
        return reb;
    }

    public void setReb(float reb) {
        this.reb = reb;
    }

    public float getAst() {
        return ast;
    }

    public void setAst(float ast) {
        this.ast = ast;
    }

    public float getStl() {
        return stl;
    }

    public void setStl(float stl) {
        this.stl = stl;
    }

    public float getBlk() {
        return blk;
    }

    public void setBlk(float blk) {
        this.blk = blk;
    }

    public float getPts() {
        return pts;
    }

    public void setPts(float pts) {
        this.pts = pts;
    }

    protected Player(Parcel in) {
        name = in.readString();
        fgpct = in.readFloat();
        reb = in.readFloat();
        ast = in.readFloat();
        stl = in.readFloat();
        blk = in.readFloat();
        pts = in.readFloat();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeFloat(fgpct);
        dest.writeFloat(reb);
        dest.writeFloat(ast);
        dest.writeFloat(stl);
        dest.writeFloat(blk);
        dest.writeFloat(pts);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };
}
