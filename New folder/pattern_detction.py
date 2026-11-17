def detect_patterns(history, today_data):
    patterns = []
    if len(history) >= 3:
        high_screen_days = sum(1 for h in history if h.get("screen_time_hours", 0) > 6)
        if high_screen_days >= 2:
            patterns.append("High screen time detected frequently over recent logs.")

    if today_data.get("sweet_intake_grams", 0) > 70 and today_data.get("sleep_hours", 0) < 6:
        patterns.append("High sugar intake combined with low sleep detected today.")

    if not patterns:
        patterns.append("No adverse behavioral patterns detected.")

    return patterns