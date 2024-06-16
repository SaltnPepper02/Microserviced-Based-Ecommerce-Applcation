import React from 'react'
import MainCarousel from '../../components/HomeCarousel/MainCarousel'
import SectionCarousel from '../../components/HomeSecttionCarousel/SectionCarousel'
import { mens_stuff } from '../../../Data/mens_stuff'

const Homepage = () => {
  return (
    <div>
        <MainCarousel/>

        <div className='py-20 space-y-10 flex flex-col justify-center px-5 lg:px-10'>
            <SectionCarousel data={mens_stuff} sectionName={"Men's Stuff"}/>
            <SectionCarousel data={mens_stuff} sectionName={"Men's Stuff"}/>
            <SectionCarousel data={mens_stuff} sectionName={"Men's Stuff"}/>
            <SectionCarousel data={mens_stuff} sectionName={"Men's Stuff"}/>
            <SectionCarousel data={mens_stuff} sectionName={"Men's Stuff"}/>
        </div>
    </div>
  )
}

export default Homepage