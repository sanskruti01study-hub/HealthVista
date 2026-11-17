def calculate_personal_baseline(history, today_data):
    if not history:
        return {"message": "No historical data available for baseline calculation yet."}

    avg_steps = sum(h.get("steps", 0) for h in history) / len(history)
    avg_sleep = sum(h.get("sleep_hours", 0) for h in history) / len(history)
    avg_screen = sum(h.get("screen_time_hours", 0) for h in history) / len(history)

    return {
        "baseline_avg_steps": round(avg_steps, 2),
        "baseline_avg_sleep": round(avg_sleep, 2),
        "baseline_avg_screen_time": round(avg_screen, 2),
        "today_vs_baseline_steps": today_data.get("steps", 0) - avg_steps
    }