package net.warvale.scorch.sql;

import com.google.common.base.Joiner;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    The following code is from GameCore.
 */


public class SQLUtil {
    private static String createTable(String table, Map<String, Map.Entry<SQLDataType, Integer>> data) {
        List<String> set = new ArrayList<>();
        for (Map.Entry<String, Map.Entry<SQLDataType, Integer>> entry : data.entrySet()) {
            set.add("`" + entry.getKey() + "` " + entry.getValue().getKey().append(entry.getValue().getValue()));
        }
        return "CREATE TABLE `" + table + "` (" + Joiner.on(",").join(set) + ");";

    }

    private static String query(String table, String variable, Where where) {
        return "SELECT " + getChar(variable) + variable + getChar(variable) + " FROM `" + table + "` WHERE " + where.getWhere();
    }

    private static String update(String table, String variable, Object to, Where where) {
        return "UPDATE `" + table + "` SET " + getChar(variable) + variable + getChar(variable) + "=\"" + to + "\" " +
                "WHERE " + where.getWhere();
    }

    private static String execute(String table, Map<String, Object> preset) {
        List<String> pre = new ArrayList<>();
        List<String> suf = new ArrayList<>();
        for (Map.Entry<String, Object> entry : preset.entrySet()) {
            pre.add("`" + entry.getKey() + "`");
            suf.add("'" + entry.getValue() + "'");
        }
        return "INSERT INTO `" + table + "`(" + Joiner.on(",").join(pre) + ") VALUES (" + Joiner.on(",").join(suf) +
                ")";
    }

    public static boolean execute(SQLConnection connection, String table, Map<String, Object> preset) throws ClassNotFoundException, SQLException {
        return connection.executeSQL(execute(table, preset));
    }

    public static int update(SQLConnection connection, String table, String variable, Object to, Where where) throws ClassNotFoundException, SQLException {
        return connection.updateSQL(update(table, variable, to, where));
    }

    public static ResultSet query(SQLConnection connection, String table, String variable, Where where) throws ClassNotFoundException, SQLException {
        return connection.querySQL(query(table, variable, where));
    }

    public static boolean createTable(SQLConnection sql, String table, Map<String, Map.Entry<SQLDataType, Integer>> data) throws ClassNotFoundException, SQLException {
        return sql.executeSQL(createTable(table, data));
    }

    public static boolean tableExists(SQLConnection connection, String table) throws ClassNotFoundException, SQLException {
        checkAndOpen(connection);
        Connection c = connection.getConnection();
        DatabaseMetaData metaData = c.getMetaData();
        ResultSet rs = metaData.getTables(null, null, table, null);
        if (rs.next()) {
            return rs.getRow() == 1;
        }
        return false;
    }

    @Deprecated
    private static void checkAndOpen(SQLConnection connection) throws ClassNotFoundException, SQLException {
        if (!connection.checkConnection()) {
            connection.openConnection();
        }
    }

    public static String compress(Map<String, Object> data) {
        String[] parse = new String[data.size()];
        int i = 0;
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            parse[i] = entry.getKey() + ":" + entry.getValue();
            i++;
        }
        return Joiner.on(";").join(parse);
    }

    public static Map<String, Object> decompress(String s) {
        String[] unparse = s.split(";");
        Map<String, Object> unparsed = new HashMap<>();
        for (String string : unparse) {
            String[] parseit = string.split(":");
            unparsed.put(parseit[0], parseit[1]);
        }
        return unparsed;
    }

    private static String getChar(String var) {
        if (var.equalsIgnoreCase("*")) {
            return "";
        }
        return "`";
    }

    public static enum SQLDataType {
        INT("INT"), TEXT("TEXT"), VARCHAR("VARCHAR"), VARINT("VARINT");
        final String output;

        SQLDataType(String output) {
            this.output = output;
        }

        String append(int max) {
            if (max == -1) {
                return output;
            }
            return output + "(" + String.valueOf(max) + ")";
        }
    }

    public static class Where {
        private final String down;

        public Where(String down) {
            this.down = down;
        }

        public String getWhere() {
            return down;
        }
    }

    public static class WhereVar extends Where {
        public WhereVar(String var, Object value) {
            super("`" + var + "`=\"" + value + "\"");
        }
    }
}
