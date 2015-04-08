<div class="news">
{foreach $news as $n}
<div class="block">
			<div> <h3>{$n['topic']}</h3> </div>
			<div class="wordwrap"> {$n['article']}</div>
			<div class="news_date"> {$n['news_date']} </div>
			<div class="news_username">{$n['user_name']}</div>
			</div>
			{/foreach}
		
</div>