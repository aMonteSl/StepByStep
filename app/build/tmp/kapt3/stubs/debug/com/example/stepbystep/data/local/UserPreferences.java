package com.example.stepbystep.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR$\u0010\f\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000bR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/example/stepbystep/data/local/UserPreferences;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "value", "", "athleteDescription", "getAthleteDescription", "()Ljava/lang/String;", "setAthleteDescription", "(Ljava/lang/String;)V", "athleteName", "getAthleteName", "setAthleteName", "prefs", "Landroid/content/SharedPreferences;", "Companion", "app_debug"})
public final class UserPreferences {
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences prefs = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PREFS_NAME = "user_preferences";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_ATHLETE_NAME = "athlete_name";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_ATHLETE_DESCRIPTION = "athlete_description";
    @org.jetbrains.annotations.NotNull()
    public static final com.example.stepbystep.data.local.UserPreferences.Companion Companion = null;
    
    public UserPreferences(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAthleteName() {
        return null;
    }
    
    public final void setAthleteName(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAthleteDescription() {
        return null;
    }
    
    public final void setAthleteDescription(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/example/stepbystep/data/local/UserPreferences$Companion;", "", "()V", "KEY_ATHLETE_DESCRIPTION", "", "KEY_ATHLETE_NAME", "PREFS_NAME", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}