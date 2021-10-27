package pt.ipp.estg.seniorsafety.BD_ROOM;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName ="medicamentos")
public class Medicamento implements Parcelable {

    //parcelable é uma maneira de empacotar objectos


    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "date")
    private String date;

    public Medicamento(String name, String description, String date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    @Ignore
    public Medicamento() {

    }

    protected Medicamento(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        date = in.readString();
    }

    public static final Creator<Medicamento> CREATOR = new Creator<Medicamento>() {
        @Override
        public Medicamento createFromParcel(Parcel in) {
            return new Medicamento(in);
        }

        @Override
        public Medicamento[] newArray(int size) {
            return new Medicamento[size];
        }
    };

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(date);
    }



    @Override
    public String toString() {
        return "Medicamento{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
