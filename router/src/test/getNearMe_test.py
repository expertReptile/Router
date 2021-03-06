#!/usr/bin/env python3.5
from . import preTest
import getNearMe
import dbconnect
import json

'''
Description: test for getNearMe api endpoint
Author: Pearce Reinsch
'''

class TestGetNearMe():
	userLat = "36.65"
	userLon = "-121.8"
	dist = "10"
	route1 = "this is a route XD"
	route2 = "this is another route XP"
	routeId1 = "-1"
	routeId2 = "-1"
	startPointLat1 = "36.67"
	startPointLat2 = "-12.8"
	startPointLon1 = "-121.81"
	startPointLon2 = "21.8"
	startPointLatNoMatch = "121.81"
	startPointLonNoMatch = "-21.8"
	userid = "123"
	routeName1 = "route1"
	routeName2 = "route2"

	# demonstrates that when searching a database with multiple routes
	# a query that has a result within the desired radius of the input coordinate
	# will only return the desired route and none of the other possible routes.
	def test_getNearMe(self):
		response = json.loads(getNearMe.getNearMe(self.userLat, self.userLon, self.dist))
		assert response[0]['idroutes'] != self.routeId2
		assert response[0]['idroutes'] == self.routeId1
			
	# demonstrates that when searching a database with any amount of routes
	# a query that has no results will not return any routes
	def test_getNearMeNoMatch(self):
		response = json.loads(getNearMe.getNearMe(self.startPointLatNoMatch, self.startPointLonNoMatch, self.dist))
		assert len(response) == 0
			
	# demonstrates that when searching a database with multiple routes
	# a query that has multiple results within the desired radius of the input
	# coordinate will return multiple routes from within that radius
	def test_getNearMeMultiMatch(self):
		response = json.loads(getNearMe.getNearMe(self.userLat, self.userLon, 410000))
		assert len(response) > 1

	def setup_method(self):
		try:
			route1list = dbconnect.insert_data_routes(self.route1,self.startPointLat1,self.startPointLon1,self.userid,self.routeName1)
			route2list = dbconnect.insert_data_routes(self.route2,self.startPointLat2,self.startPointLon2,self.userid,self.routeName2)
			route1dict = route1list[0]
			route2dict = route2list[0]
			self.routeId1 = route1dict['idroutes']
			self.routeId2 = route2dict['idroutes']
		except:
			self.routeId1 = dbconnect.insert_data_routes(self.route1,self.startPointLat1,self.startPointLon1,self.userid,self.routeName1)
			self.routeId2 = dbconnect.insert_data_routes(self.route2,self.startPointLat2,self.startPointLon2,self.userid,self.routeName2)
			print("Darn CircleCI")

	def teardown_method(self):
		dbconnect.delete_data('routes', 'userid', self.userid)
