import React, { useState } from 'react'
import AliceCarousel from 'react-alice-carousel';
import 'react-alice-carousel/lib/alice-carousel.css';
import HomeSectionCard from '../HomeSectionCard/HomeSectionCard';
import { Button } from '@mui/material';
import ArrowBackIosNewIcon from '@mui/icons-material/ArrowBackIosNew';

const SectionCarousel = ({data, sectionName}) => {
    const[activeIndex,setActiveIndex]=useState(0);

  const responsive = {
    0: { items: 1 },
    720: { items: 3 },
    1024: { items: 5.5 },
    
  };

  const slideBack=()=>setActiveIndex(activeIndex-1);
  const slideNext=()=>setActiveIndex(activeIndex+1);

  const syncActiveIndex=({item})=>setActiveIndex(item)

  const items = data.slice(0,10).map((item) => <HomeSectionCard product={item}/>)
  return (
    <div className='border'>
      <h2 className='text-2x1 font-extrabold text-gray-800 py-5'>{sectionName}</h2>
      <div className='relative p-5 border'>
        <AliceCarousel
          items={items}
          disableButtonsControls
          responsive={responsive}
          onSlideChange={syncActiveIndex}
          activeIndex={activeIndex}
        />

        {activeIndex !==items.length-5 && <Button variant="contained" className='z-50' bg-white onClick={slideNext} sx={{
          position: 'absolute', top: "8rem", right: "0rem",
          transform: "translateX(50%) rotate(90deg)", bgcolor: "white"
        }} aria-label="next">
          <ArrowBackIosNewIcon sx={{ transform: "rotate(90deg)", color: "black" }} />
        </Button>}

        {activeIndex !== 0 && <Button variant="contained" className='z-50' bg-white onClick={slideBack} sx={{
          position: 'absolute', top: "8rem", left: "-4rem",
          transform: "translateX(50%) rotate(-90deg)", bgcolor: "white"
        }} aria-label="back">
          <ArrowBackIosNewIcon sx={{ transform: "rotate(90deg)", color: "black" }} />
        </Button>}
      </div>
    </div>

  )
}

export default SectionCarousel