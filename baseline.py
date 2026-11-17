<<<<<<< HEAD
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
=======
def calculate_baseline(historical_data, current_data):
    """
    Calculates the user's normal lifestyle baseline from historical data
    and compares it with today's metrics.
    """
    if not historical_data:
        return "No historical data available."

    # Calculate average baseline values from history
    avg_sleep = sum(d.get("sleep_hours", 0) for d in historical_data) / len(historical_data)
    avg_steps = sum(d.get("steps", 0) for d in historical_data) / len(historical_data)
    avg_screen = sum(d.get("screen_time_hours", 0) for d in historical_data) / len(historical_data)

    # Current values
    today_sleep = current_data.get("sleep_hours", 0)
    today_steps = current_data.get("steps", 0)
    today_screen = current_data.get("screen_time_hours", 0)

    print("--- B. Personal Baseline Analysis ---")
    print(f"Usual sleep: {avg_sleep:.1f} hours | Today's sleep: {today_sleep} hours")
    print(f"Usual steps: {int(avg_steps):,}     | Today's steps: {today_steps:,}")
    print(f"Usual screen time: {avg_screen:.1f} hours | Today's screen: {today_screen} hours")

    # Flag differences
    insights = []
    if today_sleep < avg_sleep - 1:
        insights.append("Noticeable drop in sleep compared to your usual baseline.")
    if today_steps < avg_steps - 2000:
        insights.append("Steps are significantly lower than your normal average.")
    if today_screen > avg_screen + 1.5:
        insights.append("Screen time is notably higher than your typical baseline.")

    return insights
>>>>>>> 358c14c368c3c7af094a09200031ff8a9d0d74f2
