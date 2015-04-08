<div class="pop_up_menu">
	{if $user neq ''} 
	 Hello {$user[0]} 
	{/if}
	
	
		{if $user[0] eq ''} <a href="login.php">Log In</a>
		
			<a href="register.php">Register</a>

		{else}
		
		<a href="profile.php">Profile</a>
		<a href="logout.php">Logout</a>
		{if $user[6] eq true}
		<a href="add_news.php">Add News</a>
		{/if}
		<a href="add_review.php">Add Review</a>
		{/if}
	</div>