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