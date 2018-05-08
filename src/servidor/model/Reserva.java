package servidor.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reserva implements Serializable {

    private long id;
    private String name;
    private Date date;
    private int amount;
    private String password;
    private int state = 0;

    public Reserva() {
        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //Date date = new Date();
        //System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
    }

    public Reserva(long id, String name, Date date, int amount, String password, int state) {
        setReserva(id, name, date, amount, password, state);
    }

    public void setReserva(long id, String name, Date date, int amount, String password, int state) {
        setId(id);
        setName(name);
        setDate(date);
        setAmount(amount);
        setPassword(password);
        setState(state);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return (id + "%" + name + "%" + date + "%" + amount + "%" + password + "%" + state);
    }

    //FIXME ponme donde deberia ir
    public Object[] toObjectArray(){
        Object[] data = new Object[6];
        data[0] = id;
        data[1] = name;
        data[2] = date;
        data[3] = amount;
        data[4] = 25;
        data[5] = 15;
        return data;
    }
}
