def generate_ai_insight_and_action(today_data, score_data, patterns, trend):
    """
    Generates a human-readable AI summary and picks the single best 'One Small Change' action.
    """
    health_issues = [issue.lower() for issue in today_data.get("existing_health_issues", [])]
    has_diabetes = any("diabetes" in issue or "sugar" in issue for issue in health_issues)

    # Priority-based selection for "One Small Change"
    if has_diabetes and today_data.get("sweet_intake_servings", 0) > 0:
        one_small_change = "Swap your next sweet snack for a handful of nuts or berries to keep blood sugar steady."
    elif today_data.get("water_liters", 0) < 1.8:
        one_small_change = "Drink 1 full glass of water right now and keep a bottle near your desk."
    elif today_data.get("sleep_hours", 0) < 6.5:
        one_small_change = "Put your phone away 20 minutes earlier tonight to add deeper rest."
    elif today_data.get("step_count", 0) < 5000:
        one_small_change = "Take a brisk 10-minute walk after your next meal (+1,000 steps easily)."
    elif today_data.get("screen_time_hours", 0) > 5:
        one_small_change = "Follow the 20-20-20 rule today: every 20 mins, look 20 feet away for 20 seconds."
    else:
        one_small_change = "Maintain your great streak—try stretching for 5 minutes before bed tonight!"

    # Build AI Insight paragraph
    score = score_data["score"]
    status = score_data["status"]
    primary_pattern = patterns[0]
    trend_dir = trend["direction"].lower()

    insight = (
        f"Your Wellness Score today is {score}/100 ({status}), and your overall trend is {trend_dir}. "
        f"{primary_pattern}"
    )

    return {
        "ai_insight": insight,
        "one_small_change": one_small_change
    }