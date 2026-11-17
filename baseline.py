def calculate_personal_baseline(history, today_data):
    """
    Calculates the user's rolling average from historical logs and compares today's metrics.
    """
    if not history:
        return {
            "baseline_averages": {
                "sleep_hours": today_data.get("sleep_hours", 0),
                "step_count": today_data.get("step_count", 0),
                "water_liters": today_data.get("water_liters", 0),
                "screen_time_hours": today_data.get("screen_time_hours", 0)
            },
            "comparison": "First day logged! Establishing your personal baseline."
        }

    days = len(history)
    avg_sleep = round(sum(d.get("sleep_hours", 0) for d in history) / days, 1)
    avg_steps = round(sum(d.get("step_count", 0) for d in history) / days)
    avg_water = round(sum(d.get("water_liters", 0) for d in history) / days, 1)
    avg_screen = round(sum(d.get("screen_time_hours", 0) for d in history) / days, 1)

    highlights = []
    if today_data.get("step_count", 0) > avg_steps:
        highlights.append("Steps are above your personal average")
    elif today_data.get("step_count", 0) < avg_steps * 0.8:
        highlights.append("Steps are noticeably lower than your usual routine")

    if today_data.get("sleep_hours", 0) < avg_sleep - 1:
        highlights.append("You slept less than your usual baseline")

    if not highlights:
        highlights.append("Your habits today are right in line with your personal baseline")

    return {
        "baseline_averages": {
            "sleep_hours": avg_sleep,
            "step_count": avg_steps,
            "water_liters": avg_water,
            "screen_time_hours": avg_screen
        },
        "comparison": "; ".join(highlights)
    }