def detect_patterns(history, today_data):
    """
    Identifies behavioral correlations across recent days and today's entry.
    """
    all_days = history + [today_data]
    patterns = []

    # Pattern 1: High screen time + Low sleep
    high_screen_low_sleep = [
        d for d in all_days
        if d.get("screen_time_hours", 0) >= 6 and d.get("sleep_hours", 0) < 6.5
    ]
    if len(high_screen_low_sleep) >= 2:
        patterns.append("High screen time (6+ hrs) is consistently linked to shorter sleep on those days.")

    # Pattern 2: Low activity + High sweet intake
    low_act_high_sweet = [
        d for d in all_days
        if d.get("activity_minutes", 0) < 15 and d.get("sweet_intake_servings", 0) >= 2
    ]
    if len(low_act_high_sweet) >= 2:
        patterns.append("Sweet cravings tend to spike on days with low physical activity.")

    # Pattern 3: Dehydration streak
    low_water_days = [d for d in all_days[-3:] if d.get("water_liters", 0) < 1.5]
    if len(low_water_days) >= 2:
        patterns.append("Recurring low hydration detected over the last few days.")

    if not patterns:
        patterns.append("No negative habit loops detected—your daily balance looks steady.")

    return patterns