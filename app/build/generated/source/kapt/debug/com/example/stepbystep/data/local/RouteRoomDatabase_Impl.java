package com.example.stepbystep.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class RouteRoomDatabase_Impl extends RouteRoomDatabase {
  private volatile RouteDao _routeDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `routes` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `description` TEXT NOT NULL, `date` TEXT NOT NULL, `distance` REAL NOT NULL, `duration` INTEGER NOT NULL, `elevation` REAL NOT NULL, `elevationGain` REAL NOT NULL, `imagePath` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `points` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `routeId` INTEGER NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, `altitude` REAL NOT NULL, `timestamp` INTEGER NOT NULL, FOREIGN KEY(`routeId`) REFERENCES `routes`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_points_routeId` ON `points` (`routeId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'a8db03178d2648c8220be3a686724508')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `routes`");
        db.execSQL("DROP TABLE IF EXISTS `points`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsRoutes = new HashMap<String, TableInfo.Column>(9);
        _columnsRoutes.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoutes.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoutes.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoutes.put("date", new TableInfo.Column("date", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoutes.put("distance", new TableInfo.Column("distance", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoutes.put("duration", new TableInfo.Column("duration", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoutes.put("elevation", new TableInfo.Column("elevation", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoutes.put("elevationGain", new TableInfo.Column("elevationGain", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoutes.put("imagePath", new TableInfo.Column("imagePath", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRoutes = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRoutes = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRoutes = new TableInfo("routes", _columnsRoutes, _foreignKeysRoutes, _indicesRoutes);
        final TableInfo _existingRoutes = TableInfo.read(db, "routes");
        if (!_infoRoutes.equals(_existingRoutes)) {
          return new RoomOpenHelper.ValidationResult(false, "routes(com.example.stepbystep.data.local.RouteEntity).\n"
                  + " Expected:\n" + _infoRoutes + "\n"
                  + " Found:\n" + _existingRoutes);
        }
        final HashMap<String, TableInfo.Column> _columnsPoints = new HashMap<String, TableInfo.Column>(6);
        _columnsPoints.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPoints.put("routeId", new TableInfo.Column("routeId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPoints.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPoints.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPoints.put("altitude", new TableInfo.Column("altitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPoints.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPoints = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysPoints.add(new TableInfo.ForeignKey("routes", "CASCADE", "NO ACTION", Arrays.asList("routeId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesPoints = new HashSet<TableInfo.Index>(1);
        _indicesPoints.add(new TableInfo.Index("index_points_routeId", false, Arrays.asList("routeId"), Arrays.asList("ASC")));
        final TableInfo _infoPoints = new TableInfo("points", _columnsPoints, _foreignKeysPoints, _indicesPoints);
        final TableInfo _existingPoints = TableInfo.read(db, "points");
        if (!_infoPoints.equals(_existingPoints)) {
          return new RoomOpenHelper.ValidationResult(false, "points(com.example.stepbystep.data.local.PointEntity).\n"
                  + " Expected:\n" + _infoPoints + "\n"
                  + " Found:\n" + _existingPoints);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "a8db03178d2648c8220be3a686724508", "73cebc6ed6c1bb9e216ca0408f4b618d");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "routes","points");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `routes`");
      _db.execSQL("DELETE FROM `points`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(RouteDao.class, RouteDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public RouteDao routeDao() {
    if (_routeDao != null) {
      return _routeDao;
    } else {
      synchronized(this) {
        if(_routeDao == null) {
          _routeDao = new RouteDao_Impl(this);
        }
        return _routeDao;
      }
    }
  }
}
