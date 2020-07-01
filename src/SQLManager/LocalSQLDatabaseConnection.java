package SQLManager;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LocalSQLDatabaseConnection {

	 public static void createNewDatabase(String TeamName) {

	        String url = "jdbc:sqlite:userdata//" + TeamName +".db";

	        try (Connection connection = DriverManager.getConnection(url)) {
	            if (connection != null) {
	                DatabaseMetaData meta = connection.getMetaData();
	                System.out.println("The driver name is " + meta.getDriverName());
	                System.out.println("A new database has been created.");
	                
	                Statement statement = connection.createStatement();
	    			statement.execute("-- Exported from QuickDBD: https://www.quickdatabasediagrams.com/\r\n" + 
	    					"-- Link to schema: https://app.quickdatabasediagrams.com/#/d/8rEVms\r\n" + 
	    					"-- NOTE! If you have used non-SQL datatypes in your design, you will have to change these here.\r\n" + 
	    					"\r\n" + 
	    					"-- Modify this code to update the DB schema diagram.\r\n" + 
	    					"-- To reset the sample schema, replace everything with\r\n" + 
	    					"-- two dots ('..' - without quotes).\r\n" + 
	    					"\r\n" + 
	    					"SET XACT_ABORT ON\r\n" + 
	    					"\r\n" + 
	    					"BEGIN TRANSACTION QUICKDBD\r\n" + 
	    					"\r\n" + 
	    					"\r\n" + 
	    					"CREATE TABLE [teamproperties] (\r\n" + 
	    					"    [TeamID] INT   ,\r\n" + 
	    					"    [LastSync] TIMESTAMP ,\r\n" + 
	    					"    [TARCID] VARCHAR(20) \r\n" + 
	    					")\r\n" + 
	    					"\r\n" + 
	    					"CREATE TABLE [rockets] (\r\n" + 
	    					"    [RocketID] INT IDENTITY  ,\r\n" + 
	    					"    [RocketName] VARCHAR(30) NOT NULL   ,\r\n" + 
	    					"    [TotalCost] SMALLMONEY ,\r\n" + 
	    					"    [UserID] INT NOT NULL,\r\n" + 
	    					"    CONSTRAINT [PK_rockets] PRIMARY KEY CLUSTERED (\r\n" + 
	    					"        [RocketID] ASC\r\n" + 
	    					"    )\r\n" + 
	    					")\r\n" + 
	    					"\r\n" + 
	    					"\r\n" + 
	    					"CREATE TABLE [flights] (\r\n" + 
	    					"    [FlightID] INT IDENTITY ,\r\n" + 
	    					"    [RocketID] INT   ,\r\n" + 
	    					"    [WeatherID] INT   ,\r\n" + 
	    					"    [Location] VARCHAR(35)   ,\r\n" + 
	    					"    [Altitude] INT ,\r\n" + 
	    					"    [Time] DECIMAL(5,2)  ,\r\n" + 
	    					"    [Stability] TEXT   ,\r\n" + 
	    					"    [Notes] TEXT ,\r\n" + 
	    					"    CONSTRAINT [PK_flights] PRIMARY KEY CLUSTERED (\r\n" + 
	    					"        [FlightID] ASC\r\n" + 
	    					"    )\r\n" + 
	    					")\r\n" + 
	    					"\r\n" + 
	    					"CREATE TABLE [parts] (\r\n" + 
	    					"    [PartID] INT   ,\r\n" + 
	    					"    [Partname] VARCHAR(30)   ,\r\n" + 
	    					"    [PartURL] VARCHAR(40)   ,\r\n" + 
	    					"    [PartCost] decimal(7,3)   ,\r\n" + 
	    					"    [PartQuantity] INT   ,\r\n" + 
	    					"    [ActivePart] BIT   ,\r\n" + 
	    					"    [RocketID] INT ,\r\n" + 
	    					"    CONSTRAINT [PK_parts] PRIMARY KEY CLUSTERED (\r\n" + 
	    					"        [PartID] ASC\r\n" + 
	    					"    )\r\n" + 
	    					")\r\n" + 
	    					"\r\n" + 
	    					"CREATE TABLE [weather] (\r\n" + 
	    					"    [WeatherID] INT   ,\r\n" + 
	    					"    [Wind] INT  ,\r\n" + 
	    					"	[WindUnits] VARCHAR(10),\r\n" + 
	    					"    [Temperature] INT,\r\n" + 
	    					"    [TemperatureUnits] VARCHAR(2)   ,\r\n" + 
	    					"    [Humidity] INT   ,\r\n" + 
	    					"    CONSTRAINT [PK_weather] PRIMARY KEY CLUSTERED (\r\n" + 
	    					"        [WeatherID] ASC\r\n" + 
	    					"    )\r\n" + 
	    					")\r\n" + 
	    					"\r\n" + 
	    					"\r\n" + 
	    					"CREATE TABLE [graphs] (\r\n" + 
	    					"    [GraphID] INT   ,\r\n" + 
	    					"    [Xaxis] TEXT   ,\r\n" + 
	    					"    [Yaxis] TEXT   ,\r\n" + 
	    					"    [UserID] INT   ,\r\n" + 
	    					"    [RocketID] INT   ,\r\n" + 
	    					"    CONSTRAINT [PK_graphs] PRIMARY KEY CLUSTERED (\r\n" + 
	    					"        [GraphID] ASC\r\n" + 
	    					"    )\r\n" + 
	    					")\r\n" + 
	    					"\r\n" + 
	    					"ALTER TABLE [teamproperties] WITH CHECK ADD CONSTRAINT [FK_teamproperties_TeamID] FOREIGN KEY([TeamID])\r\n" + 
	    					"REFERENCES [teams] ([TeamID])\r\n" + 
	    					"\r\n" + 
	    					"ALTER TABLE [teamproperties] CHECK CONSTRAINT [FK_teamproperties_TeamID]\r\n" + 
	    					"\r\n" + 
	    					"ALTER TABLE [rockets] WITH CHECK ADD CONSTRAINT [FK_rockets_UserID] FOREIGN KEY([UserID])\r\n" + 
	    					"REFERENCES [teams] ([TeamID])\r\n" + 
	    					"\r\n" + 
	    					"ALTER TABLE [rockets] CHECK CONSTRAINT [FK_rockets_UserID]\r\n" + 
	    					"\r\n" + 
	    					"ALTER TABLE [rocketimages] WITH CHECK ADD CONSTRAINT [FK_rocketimages_RocketID] FOREIGN KEY([RocketID])\r\n" + 
	    					"REFERENCES [rockets] ([RocketID])\r\n" + 
	    					"\r\n" + 
	    					"ALTER TABLE [rocketimages] CHECK CONSTRAINT [FK_rocketimages_RocketID]\r\n" + 
	    					"\r\n" + 
	    					"ALTER TABLE [flights] WITH CHECK ADD CONSTRAINT [FK_flights_RocketID] FOREIGN KEY([RocketID])\r\n" + 
	    					"REFERENCES [rockets] ([RocketID])\r\n" + 
	    					"\r\n" + 
	    					"ALTER TABLE [flights] CHECK CONSTRAINT [FK_flights_RocketID]\r\n" + 
	    					"\r\n" + 
	    					"ALTER TABLE [flights] WITH CHECK ADD CONSTRAINT [FK_flights_WeatherID] FOREIGN KEY([WeatherID])\r\n" + 
	    					"REFERENCES [weather] ([WeatherID])\r\n" + 
	    					"\r\n" + 
	    					"ALTER TABLE [flights] CHECK CONSTRAINT [FK_flights_WeatherID]\r\n" + 
	    					"\r\n" + 
	    					"ALTER TABLE [parts] WITH CHECK ADD CONSTRAINT [FK_parts_RocketID] FOREIGN KEY([RocketID])\r\n" + 
	    					"REFERENCES [rockets] ([RocketID])\r\n" + 
	    					"\r\n" + 
	    					"ALTER TABLE [parts] CHECK CONSTRAINT [FK_parts_RocketID]\r\n" + 
	    					"\r\n" + 
	    					"ALTER TABLE [videos] WITH CHECK ADD CONSTRAINT [FK_videos_VideoID] FOREIGN KEY([VideoID])\r\n" + 
	    					"REFERENCES [flights] ([VideoID])\r\n" + 
	    					"\r\n" + 
	    					"ALTER TABLE [videos] CHECK CONSTRAINT [FK_videos_VideoID]\r\n" + 
	    					"\r\n" + 
	    					"ALTER TABLE [graphs] WITH CHECK ADD CONSTRAINT [FK_graphs_UserID] FOREIGN KEY([UserID])\r\n" + 
	    					"REFERENCES [teams] ([TeamID])\r\n" + 
	    					"\r\n" + 
	    					"ALTER TABLE [graphs] CHECK CONSTRAINT [FK_graphs_UserID]\r\n" + 
	    					"\r\n" + 
	    					"ALTER TABLE [graphs] WITH CHECK ADD CONSTRAINT [FK_graphs_RocketID] FOREIGN KEY([RocketID])\r\n" + 
	    					"REFERENCES [rockets] ([RocketID])\r\n" + 
	    					"\r\n" + 
	    					"ALTER TABLE [graphs] CHECK CONSTRAINT [FK_graphs_RocketID]\r\n" + 
	    					"\r\n" + 
	    					"\r\n" + 
	    					"\r\n" + 
	    					"COMMIT TRANSACTION QUICKDBD");
	            }

	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }
	 
	 public static boolean isDatabaseConnected (String TeamName) {
		 String url = "jdbc:sqlite:userdata//" + TeamName + ".db";
		 try(Connection conn = DriverManager.getConnection(url)){
			 return true;
		 }
		 catch(Exception e) {
			 return false;
		 }
	 }
	 
	 
	
}
