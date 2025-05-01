package com.example.stepbystep.data.local;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.LongSparseArray;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.RelationUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class RouteDao_Impl implements RouteDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<RouteEntity> __insertionAdapterOfRouteEntity;

  private final EntityInsertionAdapter<PointEntity> __insertionAdapterOfPointEntity;

  private final EntityDeletionOrUpdateAdapter<RouteEntity> __deletionAdapterOfRouteEntity;

  private final EntityDeletionOrUpdateAdapter<PointEntity> __deletionAdapterOfPointEntity;

  private final EntityDeletionOrUpdateAdapter<RouteEntity> __updateAdapterOfRouteEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllRoutes;

  private final SharedSQLiteStatement __preparedStmtOfDeletePointsForRoute;

  public RouteDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRouteEntity = new EntityInsertionAdapter<RouteEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `routes` (`id`,`name`,`description`,`date`,`distance`,`duration`,`elevation`,`elevationGain`,`imagePath`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RouteEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDescription());
        }
        if (entity.getDate() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getDate());
        }
        statement.bindDouble(5, entity.getDistance());
        statement.bindLong(6, entity.getDuration());
        statement.bindDouble(7, entity.getElevation());
        statement.bindDouble(8, entity.getElevationGain());
        if (entity.getImagePath() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getImagePath());
        }
      }
    };
    this.__insertionAdapterOfPointEntity = new EntityInsertionAdapter<PointEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `points` (`id`,`routeId`,`latitude`,`longitude`,`altitude`,`timestamp`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PointEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getRouteId());
        statement.bindDouble(3, entity.getLatitude());
        statement.bindDouble(4, entity.getLongitude());
        statement.bindDouble(5, entity.getAltitude());
        statement.bindLong(6, entity.getTimestamp());
      }
    };
    this.__deletionAdapterOfRouteEntity = new EntityDeletionOrUpdateAdapter<RouteEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `routes` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RouteEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__deletionAdapterOfPointEntity = new EntityDeletionOrUpdateAdapter<PointEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `points` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PointEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfRouteEntity = new EntityDeletionOrUpdateAdapter<RouteEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `routes` SET `id` = ?,`name` = ?,`description` = ?,`date` = ?,`distance` = ?,`duration` = ?,`elevation` = ?,`elevationGain` = ?,`imagePath` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RouteEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDescription());
        }
        if (entity.getDate() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getDate());
        }
        statement.bindDouble(5, entity.getDistance());
        statement.bindLong(6, entity.getDuration());
        statement.bindDouble(7, entity.getElevation());
        statement.bindDouble(8, entity.getElevationGain());
        if (entity.getImagePath() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getImagePath());
        }
        statement.bindLong(10, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteAllRoutes = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM routes";
        return _query;
      }
    };
    this.__preparedStmtOfDeletePointsForRoute = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM points WHERE routeId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final RouteEntity route, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfRouteEntity.insertAndReturnId(route);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insert(final PointEntity point, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPointEntity.insert(point);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAll(final List<PointEntity> points,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPointEntity.insert(points);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final RouteEntity route, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfRouteEntity.handle(route);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final PointEntity point, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfPointEntity.handle(point);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final RouteEntity route, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfRouteEntity.handle(route);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllRoutes(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllRoutes.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteAllRoutes.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deletePointsForRoute(final long routeId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeletePointsForRoute.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, routeId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeletePointsForRoute.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<RouteWithPoints>> getAllRoutesWithPoints() {
    final String _sql = "SELECT * FROM routes ORDER BY date DESC, distance DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"points",
        "routes"}, true, new Callable<List<RouteWithPoints>>() {
      @Override
      @Nullable
      public List<RouteWithPoints> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
            final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
            final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
            final int _cursorIndexOfDistance = CursorUtil.getColumnIndexOrThrow(_cursor, "distance");
            final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
            final int _cursorIndexOfElevation = CursorUtil.getColumnIndexOrThrow(_cursor, "elevation");
            final int _cursorIndexOfElevationGain = CursorUtil.getColumnIndexOrThrow(_cursor, "elevationGain");
            final int _cursorIndexOfImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "imagePath");
            final LongSparseArray<ArrayList<PointEntity>> _collectionPoints = new LongSparseArray<ArrayList<PointEntity>>();
            while (_cursor.moveToNext()) {
              final long _tmpKey;
              _tmpKey = _cursor.getLong(_cursorIndexOfId);
              if (!_collectionPoints.containsKey(_tmpKey)) {
                _collectionPoints.put(_tmpKey, new ArrayList<PointEntity>());
              }
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshippointsAscomExampleStepbystepDataLocalPointEntity(_collectionPoints);
            final List<RouteWithPoints> _result = new ArrayList<RouteWithPoints>(_cursor.getCount());
            while (_cursor.moveToNext()) {
              final RouteWithPoints _item;
              final RouteEntity _tmpRoute;
              final long _tmpId;
              _tmpId = _cursor.getLong(_cursorIndexOfId);
              final String _tmpName;
              if (_cursor.isNull(_cursorIndexOfName)) {
                _tmpName = null;
              } else {
                _tmpName = _cursor.getString(_cursorIndexOfName);
              }
              final String _tmpDescription;
              if (_cursor.isNull(_cursorIndexOfDescription)) {
                _tmpDescription = null;
              } else {
                _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
              }
              final String _tmpDate;
              if (_cursor.isNull(_cursorIndexOfDate)) {
                _tmpDate = null;
              } else {
                _tmpDate = _cursor.getString(_cursorIndexOfDate);
              }
              final double _tmpDistance;
              _tmpDistance = _cursor.getDouble(_cursorIndexOfDistance);
              final long _tmpDuration;
              _tmpDuration = _cursor.getLong(_cursorIndexOfDuration);
              final double _tmpElevation;
              _tmpElevation = _cursor.getDouble(_cursorIndexOfElevation);
              final double _tmpElevationGain;
              _tmpElevationGain = _cursor.getDouble(_cursorIndexOfElevationGain);
              final String _tmpImagePath;
              if (_cursor.isNull(_cursorIndexOfImagePath)) {
                _tmpImagePath = null;
              } else {
                _tmpImagePath = _cursor.getString(_cursorIndexOfImagePath);
              }
              _tmpRoute = new RouteEntity(_tmpId,_tmpName,_tmpDescription,_tmpDate,_tmpDistance,_tmpDuration,_tmpElevation,_tmpElevationGain,_tmpImagePath);
              final ArrayList<PointEntity> _tmpPointsCollection;
              final long _tmpKey_1;
              _tmpKey_1 = _cursor.getLong(_cursorIndexOfId);
              _tmpPointsCollection = _collectionPoints.get(_tmpKey_1);
              _item = new RouteWithPoints(_tmpRoute,_tmpPointsCollection);
              _result.add(_item);
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getRouteById(final long routeId,
      final Continuation<? super RouteEntity> $completion) {
    final String _sql = "SELECT * FROM routes WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, routeId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<RouteEntity>() {
      @Override
      @Nullable
      public RouteEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfDistance = CursorUtil.getColumnIndexOrThrow(_cursor, "distance");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final int _cursorIndexOfElevation = CursorUtil.getColumnIndexOrThrow(_cursor, "elevation");
          final int _cursorIndexOfElevationGain = CursorUtil.getColumnIndexOrThrow(_cursor, "elevationGain");
          final int _cursorIndexOfImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "imagePath");
          final RouteEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final double _tmpDistance;
            _tmpDistance = _cursor.getDouble(_cursorIndexOfDistance);
            final long _tmpDuration;
            _tmpDuration = _cursor.getLong(_cursorIndexOfDuration);
            final double _tmpElevation;
            _tmpElevation = _cursor.getDouble(_cursorIndexOfElevation);
            final double _tmpElevationGain;
            _tmpElevationGain = _cursor.getDouble(_cursorIndexOfElevationGain);
            final String _tmpImagePath;
            if (_cursor.isNull(_cursorIndexOfImagePath)) {
              _tmpImagePath = null;
            } else {
              _tmpImagePath = _cursor.getString(_cursorIndexOfImagePath);
            }
            _result = new RouteEntity(_tmpId,_tmpName,_tmpDescription,_tmpDate,_tmpDistance,_tmpDuration,_tmpElevation,_tmpElevationGain,_tmpImagePath);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getPointsForRoute(final long routeId,
      final Continuation<? super List<PointEntity>> $completion) {
    final String _sql = "SELECT * FROM points WHERE routeId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, routeId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<PointEntity>>() {
      @Override
      @NonNull
      public List<PointEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfRouteId = CursorUtil.getColumnIndexOrThrow(_cursor, "routeId");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfAltitude = CursorUtil.getColumnIndexOrThrow(_cursor, "altitude");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final List<PointEntity> _result = new ArrayList<PointEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PointEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpRouteId;
            _tmpRouteId = _cursor.getLong(_cursorIndexOfRouteId);
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            final double _tmpAltitude;
            _tmpAltitude = _cursor.getDouble(_cursorIndexOfAltitude);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            _item = new PointEntity(_tmpId,_tmpRouteId,_tmpLatitude,_tmpLongitude,_tmpAltitude,_tmpTimestamp);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getRouteWithPointsById(final long routeId,
      final Continuation<? super RouteWithPoints> $completion) {
    final String _sql = "SELECT * FROM routes WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, routeId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, true, _cancellationSignal, new Callable<RouteWithPoints>() {
      @Override
      @Nullable
      public RouteWithPoints call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
            final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
            final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
            final int _cursorIndexOfDistance = CursorUtil.getColumnIndexOrThrow(_cursor, "distance");
            final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
            final int _cursorIndexOfElevation = CursorUtil.getColumnIndexOrThrow(_cursor, "elevation");
            final int _cursorIndexOfElevationGain = CursorUtil.getColumnIndexOrThrow(_cursor, "elevationGain");
            final int _cursorIndexOfImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "imagePath");
            final LongSparseArray<ArrayList<PointEntity>> _collectionPoints = new LongSparseArray<ArrayList<PointEntity>>();
            while (_cursor.moveToNext()) {
              final long _tmpKey;
              _tmpKey = _cursor.getLong(_cursorIndexOfId);
              if (!_collectionPoints.containsKey(_tmpKey)) {
                _collectionPoints.put(_tmpKey, new ArrayList<PointEntity>());
              }
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshippointsAscomExampleStepbystepDataLocalPointEntity(_collectionPoints);
            final RouteWithPoints _result;
            if (_cursor.moveToFirst()) {
              final RouteEntity _tmpRoute;
              final long _tmpId;
              _tmpId = _cursor.getLong(_cursorIndexOfId);
              final String _tmpName;
              if (_cursor.isNull(_cursorIndexOfName)) {
                _tmpName = null;
              } else {
                _tmpName = _cursor.getString(_cursorIndexOfName);
              }
              final String _tmpDescription;
              if (_cursor.isNull(_cursorIndexOfDescription)) {
                _tmpDescription = null;
              } else {
                _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
              }
              final String _tmpDate;
              if (_cursor.isNull(_cursorIndexOfDate)) {
                _tmpDate = null;
              } else {
                _tmpDate = _cursor.getString(_cursorIndexOfDate);
              }
              final double _tmpDistance;
              _tmpDistance = _cursor.getDouble(_cursorIndexOfDistance);
              final long _tmpDuration;
              _tmpDuration = _cursor.getLong(_cursorIndexOfDuration);
              final double _tmpElevation;
              _tmpElevation = _cursor.getDouble(_cursorIndexOfElevation);
              final double _tmpElevationGain;
              _tmpElevationGain = _cursor.getDouble(_cursorIndexOfElevationGain);
              final String _tmpImagePath;
              if (_cursor.isNull(_cursorIndexOfImagePath)) {
                _tmpImagePath = null;
              } else {
                _tmpImagePath = _cursor.getString(_cursorIndexOfImagePath);
              }
              _tmpRoute = new RouteEntity(_tmpId,_tmpName,_tmpDescription,_tmpDate,_tmpDistance,_tmpDuration,_tmpElevation,_tmpElevationGain,_tmpImagePath);
              final ArrayList<PointEntity> _tmpPointsCollection;
              final long _tmpKey_1;
              _tmpKey_1 = _cursor.getLong(_cursorIndexOfId);
              _tmpPointsCollection = _collectionPoints.get(_tmpKey_1);
              _result = new RouteWithPoints(_tmpRoute,_tmpPointsCollection);
            } else {
              _result = null;
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
            _statement.release();
          }
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private void __fetchRelationshippointsAscomExampleStepbystepDataLocalPointEntity(
      @NonNull final LongSparseArray<ArrayList<PointEntity>> _map) {
    if (_map.isEmpty()) {
      return;
    }
    if (_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      RelationUtil.recursiveFetchLongSparseArray(_map, true, (map) -> {
        __fetchRelationshippointsAscomExampleStepbystepDataLocalPointEntity(map);
        return Unit.INSTANCE;
      });
      return;
    }
    final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `id`,`routeId`,`latitude`,`longitude`,`altitude`,`timestamp` FROM `points` WHERE `routeId` IN (");
    final int _inputSize = _map.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int i = 0; i < _map.size(); i++) {
      final long _item = _map.keyAt(i);
      _stmt.bindLong(_argIndex, _item);
      _argIndex++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "routeId");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfId = 0;
      final int _cursorIndexOfRouteId = 1;
      final int _cursorIndexOfLatitude = 2;
      final int _cursorIndexOfLongitude = 3;
      final int _cursorIndexOfAltitude = 4;
      final int _cursorIndexOfTimestamp = 5;
      while (_cursor.moveToNext()) {
        final long _tmpKey;
        _tmpKey = _cursor.getLong(_itemKeyIndex);
        final ArrayList<PointEntity> _tmpRelation = _map.get(_tmpKey);
        if (_tmpRelation != null) {
          final PointEntity _item_1;
          final long _tmpId;
          _tmpId = _cursor.getLong(_cursorIndexOfId);
          final long _tmpRouteId;
          _tmpRouteId = _cursor.getLong(_cursorIndexOfRouteId);
          final double _tmpLatitude;
          _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
          final double _tmpLongitude;
          _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
          final double _tmpAltitude;
          _tmpAltitude = _cursor.getDouble(_cursorIndexOfAltitude);
          final long _tmpTimestamp;
          _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
          _item_1 = new PointEntity(_tmpId,_tmpRouteId,_tmpLatitude,_tmpLongitude,_tmpAltitude,_tmpTimestamp);
          _tmpRelation.add(_item_1);
        }
      }
    } finally {
      _cursor.close();
    }
  }
}
